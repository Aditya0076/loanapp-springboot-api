package com.enigmacamp.loanapp.controller;


import com.enigmacamp.loanapp.constant.APIUrl;
import com.enigmacamp.loanapp.model.dto.response.CommonResponse;
import com.enigmacamp.loanapp.model.dto.response.StaffResponse;
import com.enigmacamp.loanapp.service.StaffService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(APIUrl.STAFF_URL)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class StaffController {
    private final StaffService staffService;

    @PostMapping("/get-all")
    public ResponseEntity<CommonResponse<List<StaffResponse>>> getAllStaffs() {
        List<StaffResponse> staffResponses = staffService.getAllStaffs();
        CommonResponse<List<StaffResponse>> response = CommonResponse.<List<StaffResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Staff retrieved successfully")
                .data(staffResponses)
                .build();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

}
