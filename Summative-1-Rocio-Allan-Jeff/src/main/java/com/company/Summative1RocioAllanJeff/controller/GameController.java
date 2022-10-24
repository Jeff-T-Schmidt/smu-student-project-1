package com.company.Summative1RocioAllanJeff.controller;

import com.company.Summative1RocioAllanJeff.model.Game;
import com.company.Summative1RocioAllanJeff.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
// referenced record-collection activity in class on Oct. 14, 2022
@RestController
public class GameController {
    @Autowired
    private GameRepository gameRepo;
    @RequestMapping(value = "/games", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }
    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody @Valid Game customer) {
        return gameRepo.save(customer);
    }
    @GetMapping("/games/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Game> getGameById(@PathVariable Integer gameId) {
//        if (gameId > gameRepo.count())
//            throw new NotFoundException("Game doesn't exist");
        return gameRepo.findById(gameId);
    }
    @GetMapping("/games/esrbs/{esrb}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByEsrbRating(@PathVariable String esrb) {
        return gameRepo.findAllGamesByEsrb(esrb);
    }
    @GetMapping("/games/studios/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByStudio(@PathVariable String studio) {

        return gameRepo.findAllGamesByStudio(studio);
    }
    @GetMapping("/games/titles/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGameByTitle(@PathVariable String title) {

        return gameRepo.findAllGamesByTitle(title);
    }
    @PutMapping("/games")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody @Valid Game game) {

        gameRepo.save(game);
    }
    @DeleteMapping("/games/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable Integer gameId) {

        gameRepo.deleteById(gameId);
    }

}
