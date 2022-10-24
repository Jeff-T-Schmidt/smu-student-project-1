package com.company.Summative1RocioAllanJeff.controller;

import com.company.Summative1RocioAllanJeff.model.Game;
import com.company.Summative1RocioAllanJeff.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// reference record-collection activity in class on October 14, 2022
@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    GameRepository gameRepo;

    private ObjectMapper mapper = new ObjectMapper();
    Game inputGame;
    Game outputGame;
    Game outputGame2;
    Game outputGame3;

    List<Game> allGames = new ArrayList<>();
    List<Game> gamesByEsrb;
    List<Game> gamesByStudio;
    List<Game> gameByTitle;

    @Before
    public void setUp() throws Exception {
        inputGame = new Game(12, "Speedrunners", "Everyone", "Multiplayer", 32.00f, "tinyBuild", 10);
        outputGame = new Game(12, "Speedrunners", "Everyone", "Multiplayer", 32.00f, "tinyBuild", 10);
        outputGame2 = new Game(15, "Next Game", "Mature", "FPS", 15.00f, "Next Studio",10);
        outputGame3 = new Game(31, "Another Game", "Mature", "Build", 12.00f, "Another Studio", 10);
        allGames.add(outputGame);

        allGames = new ArrayList<>(Arrays.asList(
                outputGame,
                outputGame2,
                outputGame3
        ));

        gamesByEsrb = new ArrayList<>(Arrays.asList(
                outputGame2,
                outputGame3
        ));
        gamesByStudio = new ArrayList<>(Arrays.asList(
                outputGame,
                outputGame2
        ));
        gameByTitle = new ArrayList<>(Arrays.asList(
                outputGame,
                outputGame2
        ));
        doReturn(allGames).when(gameRepo).findAll();
        doReturn(outputGame).when(gameRepo).save(inputGame);
        doReturn(Optional.of(outputGame)).when(gameRepo).findById(12);
        doReturn(gamesByEsrb).when(gameRepo).findAllGamesByEsrb("Mature");
        doReturn(gamesByStudio).when(gameRepo).findAllGamesByStudio("tinyBuild");
        doReturn(gameByTitle).when(gameRepo).findAllGamesByTitle("Speedrunners");
    }
    @Test
    public void shouldReturnAllGames() throws Exception {
        String outputJson = mapper.writeValueAsString(allGames);

        mockMvc.perform(
                        get("/games"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson)
                );
    }
    @Test
    public void shouldAddGameOnPostRequest() throws Exception {
        String inputJson = mapper.writeValueAsString(inputGame);
        String outputJson = mapper.writeValueAsString(outputGame);

        mockMvc.perform(post("/games")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }
    @Test
    public void shouldGetGameById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputGame);

        mockMvc.perform(get("/games/12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
    @Test
    public void shouldGetGamesByEsrb() throws Exception {
        String outputJson = mapper.writeValueAsString(gamesByEsrb);

        mockMvc.perform(get("/games/esrbs/Mature"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
    @Test
    public void shouldGetGamesByStudio() throws Exception {
        String outputJson = mapper.writeValueAsString(gamesByStudio);

        mockMvc.perform(get("/games/studios/tinyBuild"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
    @Test
    public void shouldGetGameByTitle() throws Exception {
        String outputJson = mapper.writeValueAsString(gameByTitle);

        mockMvc.perform(get("/games/titles/Speedrunners"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
    @Test
    public void shouldThrowA204WhenUpdatingAGame() throws Exception {
        inputGame.setGameId(12);
        inputGame.setEsrb("Mature");

        String inputJson = mapper.writeValueAsString(inputGame);

        mockMvc.perform(put("/games")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent());
    }
    @Test
    public void shouldThrowA204WhenDeletingAGame() throws Exception {
        mockMvc.perform(delete("/games/12"))
                .andExpect(status().isNoContent());
    }
}