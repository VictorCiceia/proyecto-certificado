package com.project.certified.services.Mongo;

import com.project.certified.dto.LoanDto;
import com.project.certified.entity.Mongo.LoanEntity;
import com.project.certified.exception.ResourceNotFoundException;
import com.project.certified.repository.Mongo.LoanRepositoryMongo;
import com.project.certified.services.LoanService;
import com.project.certified.services.mapper.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceMongo implements LoanService {

    @Autowired
    private LoanRepositoryMongo loanRepository;

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
    public List<LoanDto> findAllByBook(String idBook) {
        return List.of();
    }

    @Override
    public List<LoanDto> findAllByUser(String idUser) {
        return List.of();
    }

    @Override
    public LoanDto save(LoanDto loanDto) {
        final LoanEntity loan = mapper.toEntityMongo(loanDto);
        final LoanEntity savedLoan = loanRepository.save(loan);
        return mapper.toDto(savedLoan);
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
