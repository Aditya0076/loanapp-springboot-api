package com.enigmacamp.loanapp.model.dto.response;

import com.enigmacamp.loanapp.model.entity.InstalmentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstalmentTypeResponse {

    @JsonProperty("instalment_type_id")
    private String id;

    @JsonProperty("instalment_type")
    private InstalmentType.EInstalmentType instalmentType;
}
