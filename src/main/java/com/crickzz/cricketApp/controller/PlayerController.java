package com.crickzz.cricketApp.controller;

import com.crickzz.cricketApp.model.Match;
import com.crickzz.cricketApp.model.Player;
import com.crickzz.cricketApp.service.MatchService;
import com.crickzz.cricketApp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private MatchService matchService;
    @GetMapping
    public List<Player> getAllPlayer() {
        return playerService.getAllPlayer();
    }
    @GetMapping("/{playerId}")
    public Player getPlayer(@PathVariable int playerId) {
        return playerService.getPlayerById(playerId);
    }
    @GetMapping("/name/{playerName}")
    public List<Player> getPlayer(@PathVariable String playerName) {
        return playerService.getPlayerByName(playerName);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addPlayer(@RequestBody Player player){
        return playerService.addPlayer(player);
    }
    @DeleteMapping("/{playerId}")
    public String deletePlayer(@PathVariable int playerId){
        return playerService.deletePlayer(playerId);
    }
    @PutMapping
    public String editPlayer(@RequestBody Player player) {
        return playerService.editPlayer(player);
    }
    @GetMapping("{playerId}/stats")
    public List<Match> getPlayerStats(@PathVariable int playerId){
        return playerService.stats(playerId);
    }
    @GetMapping("/matches/{matchType}")
    public List<Match> matchList(@PathVariable String matchType) {
        return playerService.matches(matchType);
    }
    @GetMapping("/ranking/{role}/{matchType}")
    public List<?> ranking(@PathVariable String role, @PathVariable String matchType) {
        return playerService.ranking(role, matchType);
    }
}
