package com.enigmacamp.loanapp.controller;

import com.enigmacamp.loanapp.constant.APIUrl;
import com.enigmacamp.loanapp.model.dto.request.InstalmentTypeRequest;
import com.enigmacamp.loanapp.model.dto.response.CommonResponse;
import com.enigmacamp.loanapp.model.dto.response.InstalmentTypeResponse;
import com.enigmacamp.loanapp.model.entity.InstalmentType;
import com.enigmacamp.loanapp.service.InstalmentTypeService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIUrl.INSTALMENT_TYPE_URL)
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Api(tags = "Instalment Type")
public class InstalmentTypeController {
    private final InstalmentTypeService instalmentTypeService;

    @PostMapping
    public ResponseEntity<CommonResponse<InstalmentTypeResponse>> createInstalmentType(@RequestBody InstalmentTypeRequest instalmentTypeRequest) {
        InstalmentTypeResponse instalmentType = instalmentTypeService.createInstalmentType(instalmentTypeRequest);
        InstalmentTypeResponse instalmentTypeResponse = InstalmentTypeResponse.builder()
                .id(instalmentType.getId())
                .instalmentType(InstalmentType.EInstalmentType.valueOf(String.valueOf(instalmentType.getInstalmentType())))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<InstalmentTypeResponse>builder()
                        .data(instalmentTypeResponse)
                        .message("Instalment type created successfully")
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
    }
}
