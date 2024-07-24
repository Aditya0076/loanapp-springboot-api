package com.enigmacamp.loanapp.service;

import com.enigmacamp.loanapp.model.dto.request.LoanTransactionRequest;
import com.enigmacamp.loanapp.model.dto.response.LoanTransactionResponse;
import java.util.List;

public interface LoanTransactionService {
    LoanTransactionResponse createLoanTransaction(LoanTransactionRequest loanTransactionRequest);
    List <LoanTransactionResponse> getAllLoanTransactions();
    LoanTransactionResponse getLoanTransactionById(String id);
    LoanTransactionResponse updateLoanTransactionById(String id, LoanTransactionRequest loanTransactionRequest);
    LoanTransactionResponse deleteLoanTransactionById(String id);
    LoanTransactionResponse updateLoanStatusById(String id, LoanTransactionRequest loanTransactionRequest);
}
