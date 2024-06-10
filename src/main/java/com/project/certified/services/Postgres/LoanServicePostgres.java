package com.project.certified.services.Postgres;

import com.project.certified.dto.LoanDto;
import com.project.certified.entity.Postgres.LoanEntity;
import com.project.certified.repository.Postgres.LoanRepositoryPostgres;
import com.project.certified.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServicePostgres implements LoanService {

    @Autowired
    private LoanRepositoryPostgres loanRepository;

    @Override
    public List<LoanDto> findAll() {
        return List.of();
    }

    @Override
    public LoanDto findById(String id) {
        return null;
    }

    @Override
    public LoanDto save(LoanDto loanDto) {
        return null;
    }

    @Override
    public LoanDto update(LoanDto loanDto, String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
