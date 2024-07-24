package com.enigmacamp.loanapp.service;


import com.enigmacamp.loanapp.model.dto.request.CustomerRequest;
import com.enigmacamp.loanapp.model.dto.response.CustomerResponse;

import java.io.IOException;
import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest) throws IOException;

    List<CustomerResponse> getAllCustomers();

    CustomerResponse getCustomerById(String id);

    CustomerResponse updateCustomer(String id, CustomerRequest customerRequest);

    CustomerResponse deleteCustomer(String id);
}
