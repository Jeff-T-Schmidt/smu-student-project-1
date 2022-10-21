package com.company.Summative1RocioAllanJeff.repository;

import com.company.Summative1RocioAllanJeff.model.TaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxRatesRepository extends JpaRepository<TaxRate, String>{


}
