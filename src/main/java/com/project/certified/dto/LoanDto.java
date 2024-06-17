package com.project.certified.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {

    private String id;

    private BookDto book;

    private UserDto user;

    private Boolean delivered;

    private LocalDate loanDate;

    private LocalDate returnDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
