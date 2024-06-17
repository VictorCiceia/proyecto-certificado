package com.project.certified.services;

import com.project.certified.dto.LoanDto;

import java.util.List;

public interface LoanService {

    List<LoanDto> findAll();

    LoanDto findById(String id);

    List<LoanDto> findAllByBook(final String idBook);

    List<LoanDto> findAllByUser(final String idUser);

    LoanDto save(LoanDto loanDto);

    LoanDto update(LoanDto loanDto, String id);

    void deleteById(String id);

}
