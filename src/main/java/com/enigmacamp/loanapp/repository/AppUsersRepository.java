package com.enigmacamp.loanapp.repository;

import com.enigmacamp.loanapp.model.entity.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface AppUsersRepository extends JpaRepository<AppUsers, String> {
   @Query(nativeQuery = true, value = "select * from app_users where email = :email")
   Optional <AppUsers> findByEmail(@Param("email") String email);
}
