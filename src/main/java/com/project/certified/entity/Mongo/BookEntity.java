package com.project.certified.entity.Mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Document(collection = "books")
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    @Id
    private String id;

    private String title;

    private String author;

    private Boolean reserved;

    private Set<LoanEntity> loans;

    @Field("created_at")
    private LocalDateTime createdAt;

    @Field("updated_at")
    private LocalDateTime updatedAt;
}
