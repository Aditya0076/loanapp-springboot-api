package com.enigmacamp.loanapp.repository;

import com.enigmacamp.loanapp.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, String> {
    @Query(nativeQuery = true, value = "select * from role where role = :role")
    Role findByRole(@Param("role") Role.ERole role);

}
