package com.enigmacamp.loanapp.model.dto.request;


import com.enigmacamp.loanapp.model.entity.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransactionRequest {
    @JsonProperty("loan_type")
    private LoanType loanType;
    @JsonProperty("instalment_type")
    private InstalmentType instalmentType;
    @JsonProperty("customer")
    private Customer customer;
    @JsonProperty("nominal")
    private Double nominal;
    @JsonProperty("approved_at")
    private Long approvedAt;
    @JsonProperty("approved_by")
    private String approvedBy;
    @JsonProperty("approval_status")
    private LoanTransaction.ApprovalStatus approvalStatus;

    @JsonProperty("loan_transaction_details")
    private List<LoanTransactionDetail> loanTransactionDetails;

    @JsonProperty("created_at")
    private Long createdAt;

    @JsonProperty("updated_at")
    private Long updatedAt;

}
