package com.project.certified.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ExceptionDto {

    final String[] message;

    final int code;

    final String status;

}