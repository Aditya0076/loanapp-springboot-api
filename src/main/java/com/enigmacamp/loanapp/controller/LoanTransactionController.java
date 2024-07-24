package com.enigmacamp.loanapp.controller;

import com.enigmacamp.loanapp.constant.APIUrl;
import com.enigmacamp.loanapp.model.dto.request.LoanTransactionRequest;
import com.enigmacamp.loanapp.model.dto.response.CommonResponse;
import com.enigmacamp.loanapp.model.dto.response.LoanTransactionResponse;
import com.enigmacamp.loanapp.service.LoanTransactionService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.LOAN_TRANSACTION_URL)
@SecurityRequirement(name = "bearerAuth")
@Api(tags = "Loan Transaction", description = "Loan Transaction API")
public class LoanTransactionController {
    private final LoanTransactionService loanTransactionService;

    @PostMapping("/create")
    public ResponseEntity<CommonResponse<LoanTransactionResponse>> createLoanTransaction(@RequestBody LoanTransactionRequest loanTransactionRequest) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.createLoanTransaction(loanTransactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<LoanTransactionResponse>builder()
                        .data(loanTransactionResponse)
                        .message("Loan transaction created successfully")
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
    }

    @GetMapping()
    public ResponseEntity<CommonResponse<List<LoanTransactionResponse>>> getLoanTransaction() {
        List<LoanTransactionResponse> loanTransactionResponse = loanTransactionService.getAllLoanTransactions();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<LoanTransactionResponse>>builder()
                        .data(loanTransactionResponse)
                        .message("Loan transaction retrieved successfully")
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<LoanTransactionResponse>> getLoanTransactionById(@PathVariable String id) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.getLoanTransactionById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<LoanTransactionResponse>builder()
                        .data(loanTransactionResponse)
                        .message("Loan transaction retrieved successfully")
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<LoanTransactionResponse>> deleteLoanTransaction(@PathVariable String id) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.deleteLoanTransactionById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<LoanTransactionResponse>builder()
                        .data(loanTransactionResponse)
                        .message("Loan transaction deleted successfully")
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CommonResponse<LoanTransactionResponse>> updateLoanTransaction(@PathVariable String id, @RequestBody LoanTransactionRequest loanTransactionRequest) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.updateLoanTransactionById(id, loanTransactionRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(CommonResponse.<LoanTransactionResponse>builder()
                        .data(loanTransactionResponse)
                        .message("Loan transaction updated successfully")
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .build());
    }

    @PutMapping("/update-loan-status/{id}")
    public ResponseEntity<CommonResponse<LoanTransactionResponse>> updateLoanStatus(@PathVariable String id, @RequestBody LoanTransactionRequest loanTransactionRequest) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.updateLoanStatusById(id, loanTransactionRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(CommonResponse.<LoanTransactionResponse>builder()
                        .data(loanTransactionResponse)
                        .message("Loan transaction updated successfully")
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .build());
    }

}
