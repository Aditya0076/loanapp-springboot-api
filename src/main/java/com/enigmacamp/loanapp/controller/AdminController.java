package com.enigmacamp.loanapp.controller;


import com.enigmacamp.loanapp.constant.APIUrl;
import com.enigmacamp.loanapp.model.dto.request.AdminResquest;
import com.enigmacamp.loanapp.model.dto.response.AdminResponse;
import com.enigmacamp.loanapp.model.dto.response.CommonResponse;
import com.enigmacamp.loanapp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIUrl.ADMIN_URL)
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<CommonResponse<AdminResponse>> createAdmin(@Validated @RequestBody AdminResquest adminResquest) {
        AdminResponse adminResponse = adminService.addAdmin(adminResquest);
        CommonResponse<AdminResponse> response = CommonResponse.<AdminResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Admin created successfully")
                .data(adminResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
    }
    @GetMapping("/get-all")
    public ResponseEntity<CommonResponse<List<AdminResponse>>> getAllAdmins() {
        List<AdminResponse> adminResponses = adminService.getAllAdmins();
        CommonResponse<List<AdminResponse>> response = CommonResponse.<List<AdminResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Admin retrieved successfully")
                .data(adminResponses)
                .build();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
    @GetMapping("/get-by-first-name/{firstName}")
    public ResponseEntity<CommonResponse<AdminResponse>> getAdminByFirstName(@PathVariable String firstName) {
        AdminResponse adminResponse = adminService.getAdminByFirstName(firstName);
        CommonResponse<AdminResponse> response = CommonResponse.<AdminResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Admin retrieved successfully")
                .data(adminResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
}
