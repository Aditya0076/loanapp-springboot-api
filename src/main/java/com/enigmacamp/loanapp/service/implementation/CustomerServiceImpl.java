package com.enigmacamp.loanapp.service.implementation;


import com.enigmacamp.loanapp.model.dto.request.CustomerRequest;
import com.enigmacamp.loanapp.model.dto.response.CustomerResponse;
import com.enigmacamp.loanapp.model.entity.AppUsers;
import com.enigmacamp.loanapp.model.entity.Customer;
import com.enigmacamp.loanapp.model.entity.Role;
import com.enigmacamp.loanapp.repository.AppUsersRepository;
import com.enigmacamp.loanapp.repository.CustomerRepository;
import com.enigmacamp.loanapp.repository.PhotoRepository;
import com.enigmacamp.loanapp.repository.RoleRepository;
import com.enigmacamp.loanapp.service.CustomerService;
import com.enigmacamp.loanapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AppUsersRepository appUsersRepository;
    private final RoleRepository roleRepository;
    private  final RoleService roleService;
    private final PhotoRepository photoRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) throws IOException {
        AppUsers appUsers = AppUsers.builder()
                .email(customerRequest.getUser().getEmail())
                .password(customerRequest.getUser().getPassword())
                .roles(new HashSet<>()) // Initialize with an empty set
                .build();


        // Fetch or create the role
        Role role=roleService.createRole(customerRequest.getUser().getRoles().iterator().next());

        System.out.println(role);
        // Add the role to the set of roles
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        appUsers.setRoles(roles);

        // Save the AppUsers entity
        appUsersRepository.save(appUsers);

        // Create and save the Customer entity
        Customer customer = Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .dateOfBirth(customerRequest.getDateOfBirth())
                .phone(customerRequest.getPhone())
                .status(customerRequest.getStatus())
                .user(appUsers)
                .build();
        customerRepository.save(customer);

        return buildCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findCustomerByStatus(true);
        List<CustomerResponse> list = customers.stream().map(this::buildCustomerResponse).toList();
        return list;
    }

    @Override
    public CustomerResponse getCustomerById(String id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return buildCustomerResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(String id, CustomerRequest customerRequest) {
        Customer customer = findByIdOrThrowNotFound(id);
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setPhone(customerRequest.getPhone());
        customer.setStatus(customerRequest.getStatus());
        customer.setDateOfBirth(customerRequest.getDateOfBirth());
        customerRepository.save(customer);
        return buildCustomerResponse(customer);
    }

    @Override
    public CustomerResponse deleteCustomer(String id) {
        Customer customer = findByIdOrThrowNotFound(id);
        customer.setStatus(false);
        customerRepository.save(customer);
        return buildCustomerResponse(customer);
    }

    private Customer findByIdOrThrowNotFound(String id) {
        return customerRepository.findById(id).orElseThrow();
    }

    private CustomerResponse buildCustomerResponse(Customer customer){

        CustomerResponse customerResponse = CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dateOfBirth(customer.getDateOfBirth())
                .phone(customer.getPhone())
                .status(customer.getStatus())
                .user(customer.getUser())
                .build();

        return customerResponse;
    }
}

