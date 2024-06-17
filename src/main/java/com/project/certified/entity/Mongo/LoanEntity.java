package com.project.certified.entity.Mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Document(collection = "loans")
@NoArgsConstructor
@AllArgsConstructor
public class LoanEntity {

    @Id
    private String id;

    @DBRef
    private BookEntity book;

    @DBRef
    private UserEntity user;

    private Boolean delivered;

    private LocalDate loanDate;

    private LocalDate returnDate;

    @Field("created_at")
    private LocalDateTime createdAt;

    @Field("updated_at")
    private LocalDateTime updatedAt;

}
