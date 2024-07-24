package com.enigmacamp.loanapp.model.dto.response;

import com.enigmacamp.loanapp.model.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserResponse {

    @JsonProperty("user_id")
    private String id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("roles")
    private Set<Role> roles;
}
