package com.enigmacamp.loanapp.controller;


import com.enigmacamp.loanapp.constant.APIUrl;
import com.enigmacamp.loanapp.model.dto.request.AuthRequest;
import com.enigmacamp.loanapp.model.dto.response.AuthRespon;
import com.enigmacamp.loanapp.model.dto.response.CommonResponse;
import com.enigmacamp.loanapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(APIUrl.LOGIN_URL)
@RequiredArgsConstructor
public class SignInController {
    private final AuthService authService;

    @PostMapping()
    public ResponseEntity <CommonResponse<AuthRespon>> login(@RequestBody AuthRequest<String> request) {
        AuthRespon response = authService.login(request);
        CommonResponse<AuthRespon> commonResponse = CommonResponse.<AuthRespon>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Success Login")
                .data(response)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<CommonResponse<AuthRespon>> getUser(@PathVariable String id) {
        AuthRespon response = authService.getUser(id);
        CommonResponse<AuthRespon> commonResponse = CommonResponse.<AuthRespon>builder()
                .statusCode(HttpStatus.ACCEPTED.value())
                .message("User retrieved successfully")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(commonResponse);
    }
}
