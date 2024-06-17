package com.project.certified.services.Postgres;

import com.project.certified.dto.LoanDto;
import com.project.certified.entity.Postgres.BookEntity;
import com.project.certified.entity.Postgres.LoanEntity;
import com.project.certified.entity.Postgres.UserEntity;
import com.project.certified.exception.FailedLoanException;
import com.project.certified.exception.ResourceNotFoundException;
import com.project.certified.repository.Postgres.BookRepositoryPostgres;
import com.project.certified.repository.Postgres.LoanRepositoryPostgres;
import com.project.certified.repository.Postgres.UserRepositoryPostgres;
import com.project.certified.services.LoanService;
import com.project.certified.services.mapper.LoanMapper;
import com.project.certified.services.mapper.UserMapper;
import com.project.certified.services.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServicePostgres implements LoanService {

    @Autowired
    private LoanRepositoryPostgres loanRepository;

    @Autowired
    private BookRepositoryPostgres bookRepositoryPostgres;

    @Autowired
    private UserRepositoryPostgres userRepositoryPostgres;

    @Autowired
    private LoanMapper mapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<LoanDto> findAll() {
        return loanRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanDto> findAllByBook(final String idBook) {
        return loanRepository.findAllByBookId(idBook).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanDto> findAllByUser(final String idUser) {
        return loanRepository.findAllByUserId(idUser).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LoanDto findById(final String id) {
        return loanRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "id", id));
    }

    @Override
    public LoanDto save(final LoanDto loanDto) {
        // Validacion del libro
        BookEntity bookEntity = bookRepositoryPostgres.findById(String.valueOf(loanDto.getBook().getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", loanDto.getBook().getId()));
        if (bookEntity.getReserved()) {
            throw new FailedLoanException("El libro " + bookEntity.getTitle() + " ya se ah prestado");
        }

        //Validacion de Usuario
        final UserEntity userEntity = userRepositoryPostgres.findById(loanDto.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", loanDto.getUser().getId()));
        final LoanEntity loan = mapper.toEntityPostgre(loanDto);
        loan.setBook(bookEntity);
        loan.setUser(userEntity);
        loan.setDelivered(false);
        final LoanEntity savedLoan = loanRepository.save(loan);
        loanDto.setId(savedLoan.getId());

        //Actualiza el estado del libro
        bookEntity.setReserved(true);
        bookEntity = bookRepositoryPostgres.save(bookEntity);

        // Cargando datos para retornar
        final LoanDto dto = this.mapper.toDto(savedLoan);
        dto.setBook(this.bookMapper.toDto(bookEntity));
        dto.setUser(this.userMapper.toDto(userEntity));
        return dto;
    }

    @Override
    public LoanDto update(final LoanDto loanDto, final String id) {
        final LoanEntity loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "id", id));
        loan.setDelivered(loanDto.getDelivered());
        final LoanEntity updatedLoan = loanRepository.save(loan);
        return this.toDto(updatedLoan);
    }

    @Override
    public void deleteById(final String id) {
        final LoanEntity loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "id", id));
        loanRepository.delete(loan);
        //Actualiza el estado del libro
        final BookEntity bookEntity = bookRepositoryPostgres.findById(loan.getBook().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", loan.getBook().getId()));
        bookEntity.setReserved(false);
        bookRepositoryPostgres.save(bookEntity);
    }

    private LoanDto toDto(final LoanEntity loan) {
        final LoanDto dto = mapper.toDto(loan);
        dto.setBook(bookMapper.toDto(loan.getBook()));
        dto.setUser(userMapper.toDto(loan.getUser()));
        return dto;
    }
}
