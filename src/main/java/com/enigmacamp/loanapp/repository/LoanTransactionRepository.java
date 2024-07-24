package com.enigmacamp.loanapp.repository;

import com.enigmacamp.loanapp.model.entity.LoanTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, String> {
}
