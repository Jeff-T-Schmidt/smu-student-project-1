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

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsoleRepository repository;
    ObjectMapper mapper = new ObjectMapper();
    Console inputConsole;
    Console outputConsole;

    @Before
     public void setUp() throws Exception {
        //        input - setting arguments
         inputConsole = new Console("xbox","Sony","64gb","idk",199.99f,1);

        //        output + id to mimic postman
//        Console outputConsole = new Console(1,"xbox","Sony","64gb","idk",199.99f,1);

        outputConsole = new Console("xbox","Sony","64gb","idk",199.99f,1);
        outputConsole.setConsoleId(1);
        doReturn(outputConsole).when(repository).save(inputConsole);
    }

//    All Present: Create, Read, Read All, Update, Delete, By Manufacturer

    @Test
    public void shouldCreateNewConsoleOnPostRequest() throws Exception {

        String inputConsoleJson = mapper.writeValueAsString(inputConsole);
        String outputConsoleJson = mapper.writeValueAsString(outputConsole);

        // Act
        mockMvc.perform(post("/consoles")
                        .content(inputConsoleJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputConsoleJson));
    }


}