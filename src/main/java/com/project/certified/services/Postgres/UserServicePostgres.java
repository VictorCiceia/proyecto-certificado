package com.project.certified.services.Postgres;

import com.project.certified.dto.UserDto;
import com.project.certified.entity.Postgres.BookEntity;
import com.project.certified.entity.Postgres.LoanEntity;
import com.project.certified.entity.Postgres.UserEntity;
import com.project.certified.exception.ResourceNotFoundException;
import com.project.certified.repository.Postgres.BookRepositoryPostgres;
import com.project.certified.repository.Postgres.LoanRepositoryPostgres;
import com.project.certified.repository.Postgres.UserRepositoryPostgres;
import com.project.certified.services.UserService;
import com.project.certified.services.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServicePostgres implements UserService {

    @Autowired
    private UserRepositoryPostgres userRepositoryPostgres;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoanRepositoryPostgres loanRepository;

    @Autowired
    private BookRepositoryPostgres bookRepositoryPostgres;

    @Override
    public List<UserDto> findAll() {
        return userRepositoryPostgres.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(final String id) {
        return userRepositoryPostgres.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public UserDto update(final UserDto userDto, final String id) {
        UserEntity user = userRepositoryPostgres.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setLastName(userDto.getLastName());
        UserEntity updatedUser = userRepositoryPostgres.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteById(final String id) {
        final UserEntity user = userRepositoryPostgres.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        // Borrando todos sus prestamos
        final List<LoanEntity> loans = loanRepository.findAllByUserId(user.getId());
        for (LoanEntity loan : loans) {
            loanRepository.deleteById(loan.getId());
            //Actualiza el estado del libro
            final BookEntity bookEntity = bookRepositoryPostgres.findById(loan.getBook().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Book", "id", loan.getBook().getId()));
            bookEntity.setReserved(false);
            bookRepositoryPostgres.save(bookEntity);
        }
        userRepositoryPostgres.delete(user);
    }
}