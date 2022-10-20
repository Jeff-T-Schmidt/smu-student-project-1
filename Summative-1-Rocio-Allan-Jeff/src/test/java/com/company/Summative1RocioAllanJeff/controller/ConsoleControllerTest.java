package com.company.Summative1RocioAllanJeff.controller;

import com.company.Summative1RocioAllanJeff.model.Console;
import com.company.Summative1RocioAllanJeff.repository.ConsoleRepository;
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
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ConsoleRepository repository;
    ObjectMapper mapper = new ObjectMapper();
    Console inputConsole;
    Console outputConsole;
    Console outputConsole2;
    List<Console> allConsoles = new ArrayList<>();

    String allConsolesJson;

    @Before
     public void setUp() throws Exception {
        //        input - setting arguments
         inputConsole = new Console("xbox","Sony","64gb","idk",199.99f,1);
        //        outputs + id to mimic postman
        outputConsole = new Console("xbox","Sony","64gb","idk",199.99f,1);
        outputConsole.setConsoleId(1);


        outputConsole2 = new Console("playstation","Sony","84gb","Jaguar",249.99f,1);
        outputConsole2.setConsoleId(2);
        allConsoles.add(inputConsole);
        allConsolesJson = mapper.writeValueAsString(allConsoles);

        allConsoles = new ArrayList<>(Arrays.asList(
           outputConsole,
           outputConsole2     
        ));
    }

    // Create/POST a console
    @Test
    public void shouldCreateNewConsoleOnPostRequest() throws Exception {

        String inputConsoleJson = mapper.writeValueAsString(inputConsole);
        String outputConsoleJson = mapper.writeValueAsString(outputConsole);

        doReturn(outputConsole).when(repository).save(inputConsole);

        // Act
        mockMvc.perform(post("/consoles")
                        .content(inputConsoleJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputConsoleJson));
    }

    // Read/GET ALL consoles
    @Test
    public void shouldReturnAllConsolesOnGetRequest() throws Exception {
        String outputJson = mapper.writeValueAsString(allConsoles);

//        doReturn(allConsoles).when(repository).findAll();
        doReturn(allConsoles).
                when // conditional
                (repository).findAll(); //method we want to test

//        ResultActions result = mockMvc.perform(
            mockMvc.perform(
                        get("/consoles"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().json(outputJson)));
    }

//    TODO: Read/GET 1 console--not working need to fix
    public void shouldReturnConsoleById() throws Exception {

        String outputJson = mapper.writeValueAsString(outputConsole2);

        doReturn(Optional.of(outputConsole2)).when(repository).findById(2);

        mockMvc.perform(
                get("/consoles/2"))
                .andDo(print())
            .andExpect(status().isOk())
            .andExpect((content().json(outputJson)));
}

//    TODO: Update/PUT a console
//    TODO: Delete a console
//    TODO: Find by Manufacturer

}