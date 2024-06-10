package com.project.certified.services;

import com.project.certified.dto.LoanDto;

import java.util.List;

public interface LoanService {

    List<LoanDto> findAll();

    LoanDto findById(String id);

    LoanDto save(LoanDto loanDto);

    LoanDto update(LoanDto loanDto, String id);

    void deleteById(String id);

}
