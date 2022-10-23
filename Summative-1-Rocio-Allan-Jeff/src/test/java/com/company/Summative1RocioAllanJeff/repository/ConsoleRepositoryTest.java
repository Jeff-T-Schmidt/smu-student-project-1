package com.company.Summative1RocioAllanJeff.repository;

import com.company.Summative1RocioAllanJeff.model.Console;
import com.company.Summative1RocioAllanJeff.model.Tshirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsoleRepositoryTest {

    @Autowired
    ConsoleRepository repo;

    Console console;
    Console consoleDuplicate;
    Console console2;
    Console console3;
    @Before
    public void setUp() {
        repo.deleteAll();
//  create
        console = repo.save(new Console("xbox","Sony","64gb","idk",199.99f,1));
        consoleDuplicate = repo.save(new Console("xbox","Sony","64gb","idk",199.99f,1));
        console2 = repo.save(new Console("nintendo switch ","Nintendo Co","84gb","Jaguar",239.99f,1));
    }

    @Test
    public void findByManufacturer() {
        List<Console> NintendoCoManufacturer = repo.findByManufacturer("Nintendo Co");

        assertEquals(1, NintendoCoManufacturer.size());
    }

    @Test
    public void checkDatabaseIntegration() {
//        Read by id
        consoleDuplicate.setConsoleId(console.getConsoleId());

        Optional<Console> fromTheDatabaseOptional = repo.findById(console.getConsoleId());
        if (fromTheDatabaseOptional.isPresent()) {
            Console fromTheDatabase = fromTheDatabaseOptional.get();
            assertEquals(consoleDuplicate, fromTheDatabase);

//            Read All
            List<Console> consoleList = repo.findAll();
            assertEquals(3, consoleList.size());

//            Update
            //save the value you want to update
            fromTheDatabase.setProcessor("super fast!!");
            repo.save(fromTheDatabase);
            //call the new object with the all the properties including the updated one
            Console updatedConsole = new Console("xbox","Sony","64gb","super fast!!",199.99f,1);
            //the id
            updatedConsole.setConsoleId(fromTheDatabase.getConsoleId());
            //.get() gets the object from the optional (the amazon box is open, .get() grabs it) -- .get() tells it that the obj is there
            Console updatedConsoleFromDatabase = repo.findById(fromTheDatabase.getConsoleId()).get();
            assertEquals(updatedConsole, updatedConsoleFromDatabase);

//            Delete
            repo.deleteById(consoleDuplicate.getConsoleId());
            //When you .get() something that is not there it will throw an exception
            Optional<Console> deletedConsoleFromDB = repo.findById(consoleDuplicate.getConsoleId());
            //set to a variable
            boolean destroyedConsole = deletedConsoleFromDB.isPresent();
            //we want .isPresent to be false because the item is removed from the DB
            assertEquals(false, destroyedConsole);
        }

    }
}