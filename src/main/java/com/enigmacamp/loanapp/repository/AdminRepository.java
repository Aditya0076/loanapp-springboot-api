package com.enigmacamp.loanapp.repository;

import com.enigmacamp.loanapp.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Admin, String> {
    @Query(nativeQuery = true, value = "select * from admin where first_name = :firstName")
    Admin getAdminByFirstName(@Param("firstName") String firstName);
}
