package com.enigmacamp.loanapp.model.dto.request;

import com.enigmacamp.loanapp.model.entity.LoanTransaction;
import com.enigmacamp.loanapp.model.entity.LoanTransactionDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanTransactionDetailRequest {

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
