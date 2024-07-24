package com.enigmacamp.loanapp.service.implementation;


import com.enigmacamp.loanapp.model.dto.request.StaffRequest;
import com.enigmacamp.loanapp.model.dto.response.StaffResponse;
import com.enigmacamp.loanapp.model.entity.AppUsers;
import com.enigmacamp.loanapp.model.entity.Role;
import com.enigmacamp.loanapp.model.entity.Staff;
import com.enigmacamp.loanapp.repository.AppUsersRepository;
import com.enigmacamp.loanapp.repository.RoleRepository;
import com.enigmacamp.loanapp.repository.StaffRepository;
import com.enigmacamp.loanapp.service.RoleService;
import com.enigmacamp.loanapp.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final AppUsersRepository appUsersRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


    @Override
    public StaffResponse createStaff(StaffRequest staffRequest) {
        AppUsers appUsers = AppUsers.builder()
                .email(staffRequest.getUser().getEmail())
                .password(passwordEncoder.encode(staffRequest.getUser().getPassword()))
                .roles(new HashSet<>())
                .build();

        Role role= roleService.createRole(staffRequest.getUser().getRoles().iterator().next());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        appUsers.setRoles(roles);

        appUsersRepository.save(appUsers);

        Staff staff = staffRepository.save(Staff.builder()
                .firstName(staffRequest.getFirstName())
                .lastName(staffRequest.getLastName())
                .dateOfBirth(staffRequest.getDateOfBirth())
                .phone(staffRequest.getPhone())
                .status(staffRequest.getStatus())
                .user(appUsers)
                .build());
        return buildStaffResponse(staff);
    }

    @Override
    public List<StaffResponse> getAllStaffs() {
        List<Staff> staffs = staffRepository.findAll();
        return staffs.stream().map(this::buildStaffResponse).toList();
    }

    private StaffResponse buildStaffResponse(Staff staff) {
        return StaffResponse.builder()
                .id(staff.getId())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .dateOfBirth(staff.getDateOfBirth())
                .status(staff.getStatus())
                .phone(staff.getPhone())
                .user(staff.getUser())
                .build();
    }
}
