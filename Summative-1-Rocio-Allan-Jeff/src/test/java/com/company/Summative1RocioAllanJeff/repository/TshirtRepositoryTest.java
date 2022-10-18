package com.company.Summative1RocioAllanJeff.repository;

import com.company.Summative1RocioAllanJeff.model.Tshirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TshirtRepositoryTest {
    @Autowired
    TshirtRepository repo;

    @Before
    public void setUp() throws Exception {
        repo.deleteAll();

        repo.save(new Tshirt(1, "yellow", "large", "Large yellow tshirt", 45, 10 ));
        repo.save(new Tshirt(1, "blue", "small", "small blue tshirt", 45, 10 ));
        repo.save(new Tshirt(1, "red", "medium", "medium red tshirt", 45, 10 ));
    }
    @Test
    public void shouldFindTshirtByLargeSize() {
        List<Tshirt> largeTshirts = repo.findTshirtsBySize("large");

        assertEquals(1, largeTshirts.size());
    }
    @Test
    public void shouldFindTshirtBySmallSize() {
        List<Tshirt> smallTshirts = repo.findTshirtsBySize("small");

        assertEquals(1, smallTshirts.size());
    }
    @Test
    public void shouldFindTshirtByMediumSize() {
        List<Tshirt> mediumTshirts = repo.findTshirtsBySize("medium");

        assertEquals(1, mediumTshirts.size());
    }
    @Test
    public void shouldFindTshirtByYellowColor() {
        List<Tshirt> yellowTshirts = repo.findTshirtsByColor("yellow");

        assertEquals(1, yellowTshirts.size());
    }
    @Test
    public void shouldFindTshirtByBlueColor() {
        List<Tshirt> blueTshirts = repo.findTshirtsByColor("blue");

        assertEquals(1, blueTshirts.size());
    }
    @Test
    public void shouldFindTshirtByRedColor() {
        List<Tshirt> redTshirts = repo.findTshirtsByColor("red");

        assertEquals(1, redTshirts.size());
    }
}