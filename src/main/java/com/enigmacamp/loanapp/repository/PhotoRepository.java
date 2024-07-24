package com.enigmacamp.loanapp.repository;

import com.enigmacamp.loanapp.model.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, String> {

    Optional<Photo> findByCustomer_Id(String id);
}
