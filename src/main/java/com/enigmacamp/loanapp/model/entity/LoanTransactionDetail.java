package com.enigmacamp.loanapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "loan_transaction_detail")
public class LoanTransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "loan_transaction_detail_id")
    private String id;

    private Long transactionDate;
    private Double nominal;

    @ManyToOne
    @JoinColumn(name = "loan_transaction_id")
    private LoanTransaction loanTransaction;

    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    private Long createdAt;
    private Long updatedAt;
    public enum LoanStatus {
        PAID,
        UNPAID
    }
}


