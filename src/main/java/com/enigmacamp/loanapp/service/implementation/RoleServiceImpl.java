package com.enigmacamp.loanapp.service.implementation;


import com.enigmacamp.loanapp.model.entity.Role;
import com.enigmacamp.loanapp.repository.RoleRepository;
import com.enigmacamp.loanapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        Role newRole = new Role();
        newRole.setRole(role.getRole());
        return roleRepository.saveAndFlush(newRole);
    }
}
