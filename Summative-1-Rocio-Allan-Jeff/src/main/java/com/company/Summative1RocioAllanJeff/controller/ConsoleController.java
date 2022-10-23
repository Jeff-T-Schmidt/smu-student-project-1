package com.company.Summative1RocioAllanJeff.controller;

import com.company.Summative1RocioAllanJeff.model.Console;
import com.company.Summative1RocioAllanJeff.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/consoles")
public class ConsoleController {

    @Autowired
    ConsoleRepository consoleRepository;

    //    create a console
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Console createConsole(@RequestBody @Valid Console newConsole) {
        return consoleRepository.save(newConsole);
    }

    //    get console by id
    @GetMapping("/{consoleId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Console> getConsoleById(@PathVariable Integer consoleId) {
        return consoleRepository.findById(consoleId);
    }

    //    get all consoles
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsoles() {
        return consoleRepository.findAll();
    }

    //    update console
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody @Valid Console newConsole) {
        consoleRepository.save(newConsole);
    }

    //    delete console
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable Integer id) {
        consoleRepository.deleteById(id);
    }


    //    find by Manufacturer
    @GetMapping("/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> findByManufacturer(@PathVariable String manufacturer) {
        return consoleRepository.findByManufacturer(manufacturer);
    }
}
