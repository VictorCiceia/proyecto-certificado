package com.project.certified.services.Mongo;

import com.project.certified.dto.UserDto;
import com.project.certified.entity.Mongo.BookEntity;
import com.project.certified.entity.Mongo.LoanEntity;
import com.project.certified.entity.Mongo.UserEntity;
import com.project.certified.exception.ResourceNotFoundException;
import com.project.certified.repository.Mongo.BookRepositoryMongo;
import com.project.certified.repository.Mongo.LoanRepositoryMongo;
import com.project.certified.repository.Mongo.UserRepositoryMongo;
import com.project.certified.services.UserService;
import com.project.certified.services.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceMongo implements UserService {

    @Autowired
    private UserRepositoryMongo userRepositoryMongo;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoanRepositoryMongo loanRepositoryMongo;

    @Autowired
    private BookRepositoryMongo bookRepositoryMongo;

    @Override
    public List<UserDto> findAll() {
        return userRepositoryMongo.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(final String id) {
        return userRepositoryMongo.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public UserDto update(final UserDto userDto, final String id) {
        UserEntity user = userRepositoryMongo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setLastName(userDto.getLastName());
        UserEntity updatedUser = userRepositoryMongo.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteById(final String id) {
        final UserEntity user = userRepositoryMongo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        // Borrando todos sus prestamos
        final List<LoanEntity> loans = loanRepositoryMongo.findAllByUserId(user.getId());
        for (LoanEntity loan : loans) {
            loanRepositoryMongo.deleteById(loan.getId());
            //Actualiza el estado del libro
            final BookEntity bookEntity = bookRepositoryMongo.findById(loan.getBook().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Book", "id", loan.getBook().getId()));
            bookEntity.setReserved(false);
            bookRepositoryMongo.save(bookEntity);
        }
        userRepositoryMongo.delete(user);
    }
}
