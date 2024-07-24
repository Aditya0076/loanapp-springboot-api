package com.enigmacamp.loanapp.model.dto.request;

import com.enigmacamp.loanapp.model.entity.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvatarRequest {
    @JsonProperty("file")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MultipartFile file;
    @JsonProperty("name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Customer customer;
}
