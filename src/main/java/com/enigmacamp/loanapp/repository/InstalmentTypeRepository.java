package com.enigmacamp.loanapp.repository;

import com.enigmacamp.loanapp.model.entity.InstalmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InstalmentTypeRepository extends JpaRepository<InstalmentType, String> {
    @Query(nativeQuery = true, value = "select * from instalment_type where instalment_type = :instalmentType")
    Optional<InstalmentType> findByInstalmentType(@Param("instalmentType") InstalmentType.EInstalmentType instalmentType);
}