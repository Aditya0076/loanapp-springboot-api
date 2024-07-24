package com.enigmacamp.loanapp.model.dto.request;

import com.enigmacamp.loanapp.model.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequest {
    @JsonProperty("role")
    private Role.ERole role;
}
