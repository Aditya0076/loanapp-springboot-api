package com.enigmacamp.loanapp.repository;

import com.enigmacamp.loanapp.model.entity.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUsersRepository extends JpaRepository<AppUsers, String> {

   Optional <AppUsers> findByEmail(String email);
}
