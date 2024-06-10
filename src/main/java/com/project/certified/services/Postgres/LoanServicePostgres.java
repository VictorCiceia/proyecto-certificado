package com.project.certified.services.Postgres;

import com.project.certified.entity.Postgres.LoanEntity;
import com.project.certified.repository.Postgres.LoanRepositoryPostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServicePostgres {

    @Autowired
    private LoanRepositoryPostgres loanRepository;

    public List<LoanEntity> findAll() {
        return loanRepository.findAll();
    }

    public Optional<LoanEntity> findById(Long id) {
        return loanRepository.findById(id);
    }

    public LoanEntity save(LoanEntity loan) {
        return loanRepository.save(loan);
    }

    public void deleteById(Long id) {
        loanRepository.deleteById(id);
    }
}
