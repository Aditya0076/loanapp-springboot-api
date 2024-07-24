package com.enigmacamp.loanapp.controller;


import com.enigmacamp.loanapp.constant.APIUrl;
import com.enigmacamp.loanapp.model.dto.request.CustomerRequest;
import com.enigmacamp.loanapp.model.dto.request.StaffRequest;
import com.enigmacamp.loanapp.model.dto.response.CommonResponse;
import com.enigmacamp.loanapp.model.dto.response.CustomerResponse;
import com.enigmacamp.loanapp.model.dto.response.StaffResponse;
import com.enigmacamp.loanapp.service.CustomerService;
import com.enigmacamp.loanapp.service.StaffService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping(APIUrl.SIGNUP_URL)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class SignUpController {
    private final CustomerService customerService;
    private final StaffService staffService;
    private final Date date = new Date();
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(SignUpController.class);

    @PostMapping(value = "/create-customer")
    @ApiOperation(value = "Create Customer")
    public ResponseEntity<CommonResponse<CustomerResponse>> createCustomer(@Validated @RequestBody CustomerRequest customerRequest) throws IOException {
        logger.info("Received customerRequest: {}", customerRequest);
        CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Customer created successfully")
                .data(customerResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
    }

    @PostMapping("/create-staff")
    public ResponseEntity<CommonResponse<StaffResponse>> createStaff(@Validated @RequestBody StaffRequest staffRequest) {
        StaffResponse staffResponse = staffService.createStaff(staffRequest);
        CommonResponse<StaffResponse> response = CommonResponse.<StaffResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Staff created successfully")
                .data(staffResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
    }


}
