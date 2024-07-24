package com.enigmacamp.loanapp.repository;

import com.enigmacamp.loanapp.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByRole(Role.ERole role);

}
