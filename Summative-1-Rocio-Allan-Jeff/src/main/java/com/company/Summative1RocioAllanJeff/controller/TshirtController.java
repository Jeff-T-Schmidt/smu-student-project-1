package com.company.Summative1RocioAllanJeff.controller;

import com.company.Summative1RocioAllanJeff.model.Tshirt;
import com.company.Summative1RocioAllanJeff.repository.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class TshirtController {

    @Autowired
    TshirtRepository repo;

    //GET all
    @GetMapping("/tshirts")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getALlTshirts() {
        return repo.findAll();
    }

    //GET by id
    @GetMapping("/tshirts/{t_shirt_id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Tshirt> getTshirtById(@PathVariable Integer t_shirt_id) {
        return repo.findById(t_shirt_id);
    }

    //Ambiguous handler - added 'color' to route
    //GET by color
    @GetMapping("/tshirts/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtsByColor(@PathVariable String color) {
        return repo.findTshirtsByColor(color);
    }

    //Ambiguous handler - added 'size' to route
    //GET by size
    @GetMapping("/tshirts/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtsBySize(@PathVariable String size) {
        return repo.findTshirtsBySize(size);
    }

    //Add a tshirt
    @PostMapping("/tshirts")
    @ResponseStatus(HttpStatus.CREATED)
    public Tshirt addTshirt(@RequestBody @Valid Tshirt tshirt) {
        return repo.save(tshirt);
    }

    //Update a tshirt
    @PutMapping("/tshirts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirt(@RequestBody @Valid Tshirt tshirt) {
        repo.save(tshirt);
    }

    //Delete a tshirt
    @DeleteMapping("/tshirts/{t_shirt_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable Integer t_shirt_id) {
        repo.deleteById(t_shirt_id);
    }

}
