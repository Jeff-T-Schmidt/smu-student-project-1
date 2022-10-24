package com.company.Summative1RocioAllanJeff.repository;

import com.company.Summative1RocioAllanJeff.model.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
// referenced record-collection activity in class on Oct. 14, 2022
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GameRepositoryTest {
    @Autowired
    private GameRepository gameRepo;


    @Before
    public void setUp() throws Exception {
        gameRepo.deleteAll();

        gameRepo.save(new Game(13, "Fighting Game", "Mature", "A fighting game", 40, "First Studio", 100 ));
        gameRepo.save(new Game(14, "Shooting Game", "Mature", "A shooting game", 45, "Second Studio", 110 ));
        gameRepo.save(new Game(15, "Racing Game", "Everyone", "A racing game", 50, "Third Studio", 120 ));
    }

    @Test
    public void shouldFindAllGamesByEsrb() {
        List<Game> gamesByEsrb = gameRepo.findAllGamesByEsrb("Mature");

        assertEquals(2, gamesByEsrb.size());
    }
    @Test
    public void shouldFindAllGamesByTitle() {
        List<Game> gamesByTitle = gameRepo.findAllGamesByTitle("Fighting Game");

        assertEquals(1, gamesByTitle.size());
    }
    @Test
    public void shouldFindAllGamesByStudio() {
        List<Game> gamesByStudio = gameRepo.findAllGamesByStudio("Second Studio");

        assertEquals(1, gamesByStudio.size());
    }
}