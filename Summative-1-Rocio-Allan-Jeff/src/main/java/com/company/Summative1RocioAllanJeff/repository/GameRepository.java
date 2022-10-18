package com.company.Summative1RocioAllanJeff.repository;

import com.company.Summative1RocioAllanJeff.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findAllGamesByEsrb(String esrb);
    List<Game> findAllGamesByTitle(String title);
    List<Game> findAllGamesByStudio(String studio);
}
