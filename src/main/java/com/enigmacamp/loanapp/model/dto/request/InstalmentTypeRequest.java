package com.enigmacamp.loanapp.model.dto.request;

import com.enigmacamp.loanapp.model.entity.InstalmentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstalmentTypeRequest {

    @JsonProperty("instalment_type")
    private InstalmentType.EInstalmentType instalmentType;
}
