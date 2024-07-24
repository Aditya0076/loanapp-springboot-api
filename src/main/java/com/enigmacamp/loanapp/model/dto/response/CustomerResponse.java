package com.enigmacamp.loanapp.model.dto.response;

import com.enigmacamp.loanapp.model.entity.AppUsers;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {

    @JsonProperty("customer_id")
    private String id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("data_user")
    private AppUsers user;

}
