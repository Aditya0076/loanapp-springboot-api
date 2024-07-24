package com.enigmacamp.loanapp.repository;

import com.enigmacamp.loanapp.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    List <Customer> findCustomerByStatus(boolean status);
}
