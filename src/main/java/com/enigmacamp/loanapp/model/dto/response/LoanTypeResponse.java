package com.enigmacamp.loanapp.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTypeResponse {

    @JsonProperty("loan_type_id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("max_loan")
    private Double maxLoan;
}
