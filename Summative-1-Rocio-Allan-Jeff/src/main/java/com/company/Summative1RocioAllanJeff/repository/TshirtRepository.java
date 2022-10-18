package com.company.Summative1RocioAllanJeff.repository;

import com.company.Summative1RocioAllanJeff.model.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TshirtRepository extends JpaRepository<Tshirt, Integer> {
    List<Tshirt> findTshirtsByColor(String color);
    List<Tshirt> findTshirtsBySize(String size);

    }
