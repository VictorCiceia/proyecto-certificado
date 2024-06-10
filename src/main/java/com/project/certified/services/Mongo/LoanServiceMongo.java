package com.project.certified.services.Mongo;

import com.project.certified.entity.Mongo.LoanEntity;
import com.project.certified.repository.Mongo.LoanRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceMongo {

    @Autowired
    private LoanRepositoryMongo loanRepository;

    public List<LoanEntity> findAll() {
        return loanRepository.findAll();
    }

    public Optional<LoanEntity> findById(String id) {
        return loanRepository.findById(id);
    }

    public LoanEntity save(LoanEntity loan) {
        return loanRepository.save(loan);
    }

    public void deleteById(String id) {
        loanRepository.deleteById(id);
    }
}
