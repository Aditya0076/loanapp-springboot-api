package com.enigmacamp.loanapp.repository;

import com.enigmacamp.loanapp.model.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, String> {

    @Query(nativeQuery = true, value = "select * from photo where customer_id = :customerId")
    Optional<Photo> findByCustomerId(@Param("customerId") String customerId);
}
