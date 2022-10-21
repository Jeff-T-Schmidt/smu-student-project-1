package com.company.Summative1RocioAllanJeff.repository;

import com.company.Summative1RocioAllanJeff.model.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleRepositoryTest {

    @Autowired
    private  ConsoleRepository consoleRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void findByManufacturer() {
    }

    @Test
    public void checkDatabaseIntegration() {
        Console console = new Console("xbox","Sony","64gb","idk",199.99f,1);

        consoleRepository.save(console);

//        2nd console

    Console console2 = new Console("xbox","Sony","64gb","idk",199.99f,1);
        console2.setConsoleId(console.getConsoleId());

        Optional<Console> fromTheDatabaseOptional = consoleRepository.findById(console.getConsoleId());
        Console fromTheDatabase = fromTheDatabaseOptional.get();
        assertEquals(console2, fromTheDatabase);


    }
}