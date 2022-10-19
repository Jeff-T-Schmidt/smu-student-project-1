package com.company.Summative1RocioAllanJeff.repository;

import com.company.Summative1RocioAllanJeff.model.ProcessingFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingFeesRepository extends JpaRepository<ProcessingFee, String> {

}
