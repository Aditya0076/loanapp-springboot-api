package com.enigmacamp.loanapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "loan_type")
public class LoanType {
    @Id
    @Column(name = "loan_type_id")
    private String id;

    private String type;
    @Column(name = "max_loan")
    private Double maxLoan;
}
