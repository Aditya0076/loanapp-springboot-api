package com.enigmacamp.loanapp.service.implementation;


import com.enigmacamp.loanapp.model.dto.request.AdminResquest;
import com.enigmacamp.loanapp.model.dto.response.AdminResponse;
import com.enigmacamp.loanapp.model.entity.Admin;
import com.enigmacamp.loanapp.model.entity.AppUsers;
import com.enigmacamp.loanapp.model.entity.Role;
import com.enigmacamp.loanapp.repository.AdminRepository;
import com.enigmacamp.loanapp.repository.AppUsersRepository;
import com.enigmacamp.loanapp.service.AdminService;
import com.enigmacamp.loanapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AppUsersRepository appUsersRepository;
    private  final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AdminResponse addAdmin(AdminResquest adminResquest) {
        AppUsers appUsers = AppUsers.builder()
                .email(adminResquest.getUser().getEmail())
                .password(passwordEncoder.encode(adminResquest.getUser().getPassword()))
                .roles(new HashSet<>())
                .build();

        Role role= roleService.createRole(adminResquest.getUser().getRoles().iterator().next());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        appUsers.setRoles(roles);

        appUsersRepository.save(appUsers);

        Admin admin = Admin.builder()
                .firstName(adminResquest.getFirstName())
                .lastName(adminResquest.getLastName())
                .dateOfBirth(adminResquest.getDateOfBirth())
                .phone(adminResquest.getPhone())
                .status(adminResquest.getStatus())
                .user(appUsers)
                .build();
        adminRepository.save(admin);
        return buildAdminResponse(admin);
    }

    @Override
    public List<AdminResponse> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        List<AdminResponse> list = admins.stream().map(this::buildAdminResponse).toList();
        return list;
    }

    @Override
    public AdminResponse getAdminByFirstName(String firstName) {
        Admin admin = adminRepository.getAdminByFirstName(firstName);
        return buildAdminResponse(admin);
    }

    private AdminResponse buildAdminResponse(Admin admin) {
        return AdminResponse.builder()
                .id(admin.getId())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .dateOfBirth(admin.getDateOfBirth())
                .phone(admin.getPhone())
                .status(admin.getStatus())
                .user(admin.getUser())
                .build();
    }
}
