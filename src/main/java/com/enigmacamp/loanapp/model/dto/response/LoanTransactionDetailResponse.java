package com.enigmacamp.loanapp.model.dto.response;

import com.enigmacamp.loanapp.model.entity.LoanTransaction;
import com.enigmacamp.loanapp.model.entity.LoanTransactionDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransactionDetailResponse {
    @JsonProperty("loan_transaction_detail_id")
    private String id;
    @JsonProperty("transaction_date")
    private Long transactionDate;
    @JsonProperty("nominal")
    private Double nominal;
    @JsonProperty("loan_transaction")
    private LoanTransaction loanTransaction;
    @JsonProperty("loan_status")
    private LoanTransactionDetail.LoanStatus loanStatus;
    @JsonProperty("created_at")
    private Long createdAt;
    @JsonProperty("updated_at")
    private Long updatedAt;
}
