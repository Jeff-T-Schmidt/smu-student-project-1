package com.company.Summative1RocioAllanJeff.controller;

import com.company.Summative1RocioAllanJeff.model.Console;
import com.company.Summative1RocioAllanJeff.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/consoles")
public class ConsoleController {

    @Autowired
    private ConsoleRepository consoleRepository;

//    create a console
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Console createConsole(@RequestBody Console newConsole){
        return consoleRepository.save(newConsole);
    }

//    get console by id
//Jeff's way --didn't work
    @GetMapping("/{consoleId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Console> getConsoleById(@PathVariable Integer consoleId) {
        return consoleRepository.findById(consoleId);
    }


//    my way
//    @GetMapping("/{consoleId}")
//    @ResponseStatus(HttpStatus.OK)
//    public Console getConsoleById(@PathVariable Integer consoleId) {
//    Optional<Console> returnVal = consoleRepository.findById(Integer.valueOf(String.valueOf(consoleId)));
//    if (returnVal.isPresent()) {
//        return returnVal.get();
//    } else {
//        return null;
//    }
//}

    //    get all consoles
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsoles(){ return consoleRepository.findAll(); }

//    update console
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  updateConsole(@RequestBody Console newConsole) {
        consoleRepository.save(newConsole);
    }

//    delete console
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable Integer id) {
        consoleRepository.deleteById(id);
    }
}
