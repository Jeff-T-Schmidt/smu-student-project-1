package com.company.Summative1RocioAllanJeff.controller;

import com.company.Summative1RocioAllanJeff.model.Game;
import com.company.Summative1RocioAllanJeff.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@WebMvcTest(GameControllerTest.class)
public class GameControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    GameRepository gameRepo;
    private ObjectMapper mapper = new ObjectMapper();

    Game inputGame;
    Game outputGame;

    List<Game> gameList;
    List<Game> gameListbyEsrb

    @Before
    public void setUp() throws Exception {

    }

}