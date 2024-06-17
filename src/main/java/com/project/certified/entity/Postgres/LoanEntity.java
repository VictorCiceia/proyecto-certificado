package com.project.certified.entity.Postgres;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "loans")
@NoArgsConstructor
@AllArgsConstructor
public class LoanEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    private Boolean delivered;

    @CreationTimestamp
    private LocalDate loanDate;

    @UpdateTimestamp
    private LocalDate returnDate;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updatedAt;

}