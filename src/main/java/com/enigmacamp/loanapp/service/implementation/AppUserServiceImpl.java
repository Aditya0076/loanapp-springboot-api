package com.enigmacamp.loanapp.service.implementation;


import com.enigmacamp.loanapp.model.dto.request.AppUserRequest;
import com.enigmacamp.loanapp.model.dto.response.AppUserResponse;
import com.enigmacamp.loanapp.model.entity.AppUsers;
import com.enigmacamp.loanapp.model.entity.Role;
import com.enigmacamp.loanapp.repository.AppUsersRepository;
import com.enigmacamp.loanapp.repository.RoleRepository;
import com.enigmacamp.loanapp.service.AppUserService;
import com.enigmacamp.loanapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final RoleRepository roleRepository;
    private final CustomerService customerService;
    private final AppUsersRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AppUserResponse createAppUser(AppUserRequest appUserRequest) {
        Role role = roleRepository.findByRole(appUserRequest.getRoles().iterator().next().getRole());
        roleRepository.save(role);
        AppUsers appUsers = AppUsers.builder()
                .email(appUserRequest.getEmail())
                .password(passwordEncoder.encode(appUserRequest.getPassword()))
                .roles(new HashSet<>())
                .build();
        appUsers.getRoles().add(role);
        appUserRepository.save(appUsers);
        return AppUserResponse.builder()
                .id(appUsers.getId())
                .email(appUserRequest.getEmail())
                .password(appUserRequest.getPassword())
                .roles(appUserRequest.getRoles())
                .build();
    }
}
