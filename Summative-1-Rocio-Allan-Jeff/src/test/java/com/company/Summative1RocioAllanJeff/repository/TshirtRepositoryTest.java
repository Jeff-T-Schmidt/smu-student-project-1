package com.company.Summative1RocioAllanJeff.repository;

import com.company.Summative1RocioAllanJeff.model.Tshirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TshirtRepositoryTest {
    @Autowired
    TshirtRepository repo;

    Tshirt inputYellow;
    Tshirt inputBlue;
    Tshirt inputRed;
    Tshirt anotherInputYellow;

    @Before
    public void setUp() throws Exception {
        repo.deleteAll();

        inputYellow = repo.save(new Tshirt( "yellow", "large", "Large yellow tshirt", 46, 10));
        inputBlue = repo.save(new Tshirt("blue", "small", "small blue tshirt", 47, 10));
        inputRed = repo.save(new Tshirt("red", "medium", "medium red tshirt", 48, 10));
        anotherInputYellow = repo.save(new Tshirt("yellow", "large", "Large yellow tshirt", 46, 10));
    }

    @Test
    public void checkTshirtRepoIntegration() {
        anotherInputYellow.setT_shirt_id(inputYellow.getT_shirt_id());

        Optional<Tshirt> fromTheDatabaseOptional = repo.findById(inputYellow.getT_shirt_id());
        if (fromTheDatabaseOptional.isPresent()) {
            Tshirt fromTheDataBase = fromTheDatabaseOptional.get();
            assertEquals(anotherInputYellow, fromTheDataBase);

            List<Tshirt> tshirtList = repo.findAll();
            assertEquals(4, tshirtList.size());
            //begin update test

            //save the value you want to update
            fromTheDataBase.setColor("blue");
            repo.save(fromTheDataBase);
            //call the new object with the all the properties including the updated one
            Tshirt updatedTshirt = new Tshirt("blue","large","Large yellow tshirt", 46, 10 );
            //the id
            updatedTshirt.setT_shirt_id(fromTheDataBase.getT_shirt_id());
            //.get() gets the object from the optional (the amazon box is open, .get() grabs it) -- .get() tells it that the obj is there
            Tshirt updatedTshirtFromDataBase = repo.findById(fromTheDataBase.getT_shirt_id()).get();
            assertEquals(updatedTshirt, updatedTshirtFromDataBase);

            //begin delete test
            repo.deleteById(anotherInputYellow.getT_shirt_id());

            //When you .get() something that is not there it will throw an exception
            Optional<Tshirt> deletedTshirtFromDB = repo.findById(anotherInputYellow.getT_shirt_id());
            //set to a variable
            boolean destroyedTshirt = deletedTshirtFromDB.isPresent();
            //we want .isPresent to be false because the item is removed from the DB
            assertEquals(false, destroyedTshirt);
        }

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