package com.enigmacamp.loanapp.service.implementation;

import com.enigmacamp.loanapp.model.dto.request.LoanTransactionRequest;
import com.enigmacamp.loanapp.model.dto.response.LoanTransactionResponse;
import com.enigmacamp.loanapp.model.entity.*;
import com.enigmacamp.loanapp.repository.CustomerRepository;
import com.enigmacamp.loanapp.repository.InstalmentTypeRepository;
import com.enigmacamp.loanapp.repository.LoanTransactionRepository;
import com.enigmacamp.loanapp.repository.RoleRepository;
import com.enigmacamp.loanapp.service.LoanTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanTransactionServiceImpl implements LoanTransactionService {
    private final LoanTransactionRepository loanTransactionRepository;
    private final RoleRepository roleRepository;
    private final InstalmentTypeRepository instalmentTypeRepository;
    private final CustomerRepository customerRepository;

    @Override
    public LoanTransactionResponse createLoanTransaction(LoanTransactionRequest loanTransactionRequest) {
        Customer customer = customerRepository.findById(loanTransactionRequest.getCustomer().getId()).orElse(null);
        LoanType loanType = loanTransactionRepository.findById(loanTransactionRequest.getLoanType().getId()).orElse(null).getLoanType();
        InstalmentType instalmentType = instalmentTypeRepository.findByInstalmentType(InstalmentType.EInstalmentType.valueOf(String.valueOf(loanTransactionRequest.getInstalmentType().getInstalmentType()))).get();
        Role role = roleRepository.findByRole(Role.ERole.ROLE_ADMIN);
        LoanTransaction loanTransaction = LoanTransaction.builder()
                .customer(customer)
                .loanType(loanType)
                .instalmentType(instalmentType)
                .nominal(loanTransactionRequest.getNominal())
                .approvedAt(loanTransactionRequest.getApprovedAt())
                .approvedBy(String.valueOf(role))
                .approvalStatus(loanTransactionRequest.getApprovalStatus())
                .loanTransactionDetails(loanTransactionRequest.getLoanTransactionDetails())
                .build();

        List<LoanTransactionDetail> loanTransactionDetails = loanTransactionRequest.getLoanTransactionDetails().stream()
                .map(loanTransactionDetailRequest -> LoanTransactionDetail.builder()
                        .loanTransaction(loanTransaction)
                        .nominal(loanTransactionDetailRequest.getNominal())
                        .transactionDate(loanTransactionDetailRequest.getTransactionDate())
                        .loanStatus(loanTransactionDetailRequest.getLoanStatus())
                        .createdAt(loanTransactionDetailRequest.getCreatedAt())
                        .updatedAt(loanTransactionDetailRequest.getUpdatedAt())
                        .build()).toList();
        loanTransaction.setLoanTransactionDetails(loanTransactionDetails);
        loanTransactionRepository.save(loanTransaction);
        return buildLoanTransactionResponse(loanTransaction);
    }


    @Override
    public List<LoanTransactionResponse> getAllLoanTransactions() {
        Optional<List<LoanTransaction>> loanTransactions = Optional.ofNullable(loanTransactionRepository.findAll());
        return loanTransactions.get().stream().map(this::buildLoanTransactionResponse).toList();
    }

    @Override
    public LoanTransactionResponse getLoanTransactionById(String id) {
        LoanTransaction loanTransaction = loanTransactionRepository.findById(id).orElse(null);
        return buildLoanTransactionResponse(loanTransaction);
    }

    @Override
    public LoanTransactionResponse updateLoanTransactionById(String id, LoanTransactionRequest loanTransactionRequest) {
        LoanTransaction loanTransaction = loanTransactionRepository.findById(id).orElse(null);
        loanTransaction.setLoanType(loanTransactionRequest.getLoanType());
        loanTransaction.setInstalmentType(loanTransactionRequest.getInstalmentType());
        loanTransaction.setCustomer(loanTransactionRequest.getCustomer());
        loanTransaction.setNominal(loanTransactionRequest.getNominal());
        loanTransaction.setApprovedAt(loanTransactionRequest.getApprovedAt());
        loanTransaction.setApprovedBy(loanTransactionRequest.getApprovedBy());
        loanTransaction.setApprovalStatus(loanTransactionRequest.getApprovalStatus());
        loanTransaction.setLoanTransactionDetails(loanTransactionRequest.getLoanTransactionDetails());
        loanTransaction.setCreatedAt(loanTransactionRequest.getCreatedAt());
        loanTransaction.setUpdatedAt(loanTransactionRequest.getUpdatedAt());
        loanTransactionRepository.save(loanTransaction);
        return buildLoanTransactionResponse(loanTransaction);
    }

    @Override
    public LoanTransactionResponse deleteLoanTransactionById(String id) {
        LoanTransaction loanTransaction = loanTransactionRepository.findById(id).orElse(null);
        loanTransactionRepository.delete(loanTransaction);
        return buildLoanTransactionResponse(loanTransaction);
    }

    @Override
    public LoanTransactionResponse updateLoanStatusById(String id, LoanTransactionRequest loanTransactionRequest) {
        LoanTransaction loanTransaction = loanTransactionRepository.findById(id).orElse(null);
        loanTransaction.setApprovalStatus(loanTransactionRequest.getApprovalStatus());
        loanTransactionRepository.save(loanTransaction);
        return buildLoanTransactionResponse(loanTransaction);
    }

    private LoanTransactionResponse buildLoanTransactionResponse(LoanTransaction loanTransaction) {
        return LoanTransactionResponse.builder()
                .loanType(loanTransaction.getLoanType())
                .instalmentType(loanTransaction.getInstalmentType())
                .customer(loanTransaction.getCustomer())
                .nominal(loanTransaction.getNominal())
                .approvedAt(loanTransaction.getApprovedAt())
                .approvedBy(loanTransaction.getApprovedBy())
                .approvalStatus(loanTransaction.getApprovalStatus())
                .loanTransactionDetails(loanTransaction.getLoanTransactionDetails())
                .createdAt(loanTransaction.getCreatedAt())
                .updatedAt(loanTransaction.getUpdatedAt())
                .build();
    }
}
