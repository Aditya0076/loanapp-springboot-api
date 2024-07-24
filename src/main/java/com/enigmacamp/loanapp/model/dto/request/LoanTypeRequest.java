package com.enigmacamp.loanapp.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanTypeRequest {

    @JsonProperty("type")
    private String type;

    @JsonProperty("max_loan")
    private Double maxLoan;
}
