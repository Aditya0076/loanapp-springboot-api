package com.enigmacamp.loanapp.repository;

import com.enigmacamp.loanapp.model.entity.LoanTransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTransactionDetailRepository extends JpaRepository<LoanTransactionDetail, String> {
}
