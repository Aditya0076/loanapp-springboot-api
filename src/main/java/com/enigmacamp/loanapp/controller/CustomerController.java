package com.enigmacamp.loanapp.controller;

import com.enigmacamp.loanapp.constant.APIUrl;
import com.enigmacamp.loanapp.model.dto.request.CustomerRequest;
import com.enigmacamp.loanapp.model.dto.response.AvatarResponse;
import com.enigmacamp.loanapp.model.dto.response.CommonResponse;
import com.enigmacamp.loanapp.model.dto.response.CustomerResponse;
import com.enigmacamp.loanapp.service.CustomerService;
import com.enigmacamp.loanapp.service.PhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.CUSTOMER_URL)
@SecurityRequirement(name = "bearerAuth")
@Api(tags = "Customer")
public class CustomerController {
    private final CustomerService customerService;
    private final PhotoService photoService;

    @GetMapping("/get-all")
    public ResponseEntity<CommonResponse<List<CustomerResponse>>> getAllCustomers() {
        List<CustomerResponse> customerResponses = customerService.getAllCustomers();
        CommonResponse<List<CustomerResponse>> response = CommonResponse.<List<CustomerResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Customer retrieved successfully")
                .data(customerResponses)
                .build();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> getCustomerById(@PathVariable String id) {
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Customer retrieved successfully")
                .data(customerResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
    @PutMapping("/update")
    public ResponseEntity<CommonResponse<CustomerResponse>> updateCustomer(@Validated @RequestParam String id, @RequestBody  CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.updateCustomer(id, customerRequest);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Customer updated successfully")
                .data(customerResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> deleteCustomer(@PathVariable String id) {
        CustomerResponse customerResponse = customerService.deleteCustomer(id);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Customer deleted successfully")
                .data(customerResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
    @PutMapping(value = "/upload-profile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "Upload Profile")
    public ResponseEntity<CommonResponse<AvatarResponse>> uploadProfile(@RequestParam ("file") MultipartFile file, @RequestParam ("id-customer") String id) {
        try {
            AvatarResponse avatarResponse=photoService.uploadPhotoCustomerById(id,file);
            CommonResponse<AvatarResponse> response = CommonResponse.<AvatarResponse>builder()
                    .statusCode(HttpStatus.OK.value())
                    .message("Profile uploaded successfully")
                    .data(avatarResponse)
                    .build();
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(HttpStatus.OK.value()));
        } catch (Exception e) {
            CommonResponse<AvatarResponse> response = CommonResponse.<AvatarResponse>builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .data(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }
    @GetMapping("/get-profile/{id}")
    @ApiOperation(value = "Get Profile")
    public ResponseEntity<byte[]> getProfile(@PathVariable String id) {
        try {
            Optional<AvatarResponse> avatarResponse=photoService.getPhotoCustomerById(id);
            CommonResponse<AvatarResponse> response = CommonResponse.<AvatarResponse>builder()
                    .statusCode(HttpStatus.OK.value())
                    .message("Profile retrieved successfully")
                    .data(AvatarResponse.builder()
                            .id(avatarResponse.get().getId())
                            .name(avatarResponse.get().getName())
                            .type(avatarResponse.get().getType())
                            .data(avatarResponse.get().getData())
                            .customer(CustomerResponse.builder()
                                    .id(avatarResponse.get().getCustomer().getId())
                                    .firstName(avatarResponse.get().getCustomer().getFirstName())
                                    .lastName(avatarResponse.get().getCustomer().getLastName())
                                    .dateOfBirth(avatarResponse.get().getCustomer().getDateOfBirth())
                                    .phone(avatarResponse.get().getCustomer().getPhone())
                                    .status(avatarResponse.get().getCustomer().getStatus())
                                    .user(avatarResponse.get().getCustomer().getUser())
                                    .build())
                            .build())
                    .build();
            return  ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+avatarResponse.get().getName()+ "\"").body(response.getData().getData());
        } catch (Exception e) {
            CommonResponse<AvatarResponse> response = CommonResponse.<AvatarResponse>builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getData().getData());
        }

    }
}
