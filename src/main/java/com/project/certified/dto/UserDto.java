package com.project.certified.dto;

import com.project.certified.util.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;

    private String name;

    private String lastName;

    private String email;

    private Set<LoanDto> loans;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    String password;

    Role role;

}
