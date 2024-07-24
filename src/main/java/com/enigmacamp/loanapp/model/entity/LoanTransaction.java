package com.enigmacamp.loanapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "loan_transaction")
public class LoanTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "loan_transaction_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;

    @ManyToOne
    @JoinColumn(name = "instalment_type_id")
    private InstalmentType instalmentType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Double nominal;
    private Long approvedAt;
    private String approvedBy;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    @OneToMany(mappedBy = "loanTransaction")
    private List<LoanTransactionDetail> loanTransactionDetails;

    private Long createdAt;
    private Long updatedAt;

    public enum ApprovalStatus {
        APPROVED,
        REJECTED
    }

}

