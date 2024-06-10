package com.project.certified.services.Postgres;

import com.project.certified.dto.LoanDto;
import com.project.certified.entity.Postgres.BookEntity;
import com.project.certified.entity.Postgres.LoanEntity;
import com.project.certified.entity.Postgres.UserEntity;
import com.project.certified.exception.ResourceNotFoundException;
import com.project.certified.repository.Postgres.BookRepositoryPostgres;
import com.project.certified.repository.Postgres.LoanRepositoryPostgres;
import com.project.certified.repository.Postgres.UserRepositoryPostgres;
import com.project.certified.services.LoanService;
import com.project.certified.services.mapper.LoanMapper;
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

    @Override
    public List<LoanDto> findAll() {
        return loanRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LoanDto findById(String id) {
        return loanRepository.findById(id)
                .map(this.mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "id", id));
    }

    @Override
    public LoanDto save(LoanDto loanDto) {
        final LoanEntity loan = mapper.toEntityPostgre(loanDto);
        final BookEntity bookEntity = bookRepositoryPostgres.findById(String.valueOf(loanDto.getBook().getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", loanDto.getBook().getId()));
        final UserEntity userEntity = userRepositoryPostgres.findById(loanDto.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", loanDto.getUser().getId()));
        loan.setBook(bookEntity);
        loan.setUser(userEntity);
        final LoanEntity savedLoan = loanRepository.save(loan);
        loanDto.setId(savedLoan.getId());
        return loanDto;
    }

    @Override
    public LoanDto update(LoanDto loanDto, String id) {
        final LoanEntity loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "id", id));
        final LoanEntity updatedLoan = loanRepository.save(loan);
        return mapper.toDto(updatedLoan);
    }

    @Override
    public void deleteById(String id) {
        if (!loanRepository.existsById(id)) {
            throw new ResourceNotFoundException("Loan", "id", id);
        }
        loanRepository.deleteById(id);
    }
}
