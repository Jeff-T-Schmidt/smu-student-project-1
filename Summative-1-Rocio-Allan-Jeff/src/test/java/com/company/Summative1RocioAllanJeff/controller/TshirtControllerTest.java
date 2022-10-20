package com.company.Summative1RocioAllanJeff.controller;

import com.company.Summative1RocioAllanJeff.model.Tshirt;
import com.company.Summative1RocioAllanJeff.repository.ConsoleRepository;
import com.company.Summative1RocioAllanJeff.repository.TshirtRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TshirtController.class)
public class TshirtControllerTest {
    @Autowired
    MockMvc mockMvc;


    @MockBean
    TshirtRepository repo;


    private ObjectMapper mapper = new ObjectMapper();

    Tshirt inputYellowTshirt;
    Tshirt outputYellowTshirt;

    Tshirt inputBlueTshirt;
    Tshirt outputBlueTshirt;

    List<Tshirt> largeTshirts;
    List<Tshirt> blueTshirts;


    @Before
    public void setup() throws Exception {
        inputYellowTshirt = new Tshirt(1, "yellow", "large", "Large yellow tshirt", 45, 10);
        outputYellowTshirt = new Tshirt(1, "yellow", "large", "Large yellow tshirt", 45, 10);
        outputYellowTshirt.setT_shirt_id(1);
        outputYellowTshirt.setSize("large");

        inputBlueTshirt = new Tshirt(2, "blue", "small", "small blue tshirt", 45, 10);
        outputBlueTshirt = new Tshirt(2, "blue", "small", "small blue tshirt", 45, 10);
        outputBlueTshirt.setT_shirt_id(2);
        outputBlueTshirt.setColor("blue");

        largeTshirts = new ArrayList<>(Arrays.asList(outputYellowTshirt, outputBlueTshirt));
        blueTshirts = new ArrayList<>(Arrays.asList(outputYellowTshirt, outputBlueTshirt));

        doReturn(outputYellowTshirt).when(repo).save(inputYellowTshirt);
        doReturn(Optional.of(outputYellowTshirt)).when(repo).findById(1);
        doReturn(largeTshirts).when(repo).findTshirtsBySize("large");

        doReturn(outputBlueTshirt).when(repo).save(inputBlueTshirt);
        doReturn(Optional.of(outputBlueTshirt)).when(repo).findById(2);
        doReturn(blueTshirts).when(repo).findTshirtsByColor("blue");
    }

    //Tshirt id
    @Test
    public void shouldGetTshirtsById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputYellowTshirt);

        mockMvc.perform(get("/tshirts/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
    //Tshirt size
    @Test
    public void shouldReturnTshirtsByLargeSize() throws Exception {
        String outputJson = mapper.writeValueAsString(largeTshirts);

        mockMvc.perform(get("/tshirts/size/large"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
    //Tshirt color
    @Test
    public void shouldReturnTshirtsByBlueColor() throws Exception {
        String outputJson = mapper.writeValueAsString(largeTshirts);

        mockMvc.perform(get("/tshirts/color/blue"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
    //Add new Tshirt
    @Test
    public void shouldAddATshirtOnPostRequest() throws Exception {
        String inputJson = mapper.writeValueAsString(inputBlueTshirt);
        String outputJson = mapper.writeValueAsString(outputBlueTshirt);

        mockMvc.perform(post("/tshirts")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }
    //Update a Tshirt
    @Test
    public void shouldRespondWith204WhenUpdatingATshirt() throws Exception {
        inputYellowTshirt.setT_shirt_id(5);
        inputYellowTshirt.setPrice(100);

        String inputJson = mapper.writeValueAsString(inputYellowTshirt);

        mockMvc.perform(put("/tshirts")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    //Delete a Tshirt by id
    @Test
    public void shouldRespondWith204WhenDeletingATshirt() throws Exception {
        mockMvc.perform(delete("/tshirts/1"))
                .andExpect(status().isNoContent());
    }
}