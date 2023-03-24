package com.crickzz.cricketApp.controller;

import com.crickzz.cricketApp.model.Player;
import com.crickzz.cricketApp.model.Team;
import com.crickzz.cricketApp.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;
    @GetMapping
    public List<Team> getAllTeam(){
        return teamService.getAllTeam();
    }
    @GetMapping("/{teamId}")
    public Team getTeam(@PathVariable int teamId){
        return teamService.getTeamById(teamId);
    }
    @GetMapping("/name/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        return teamService.getTeamByName(teamName);
    }
    @PostMapping
    public String addTeam(@RequestBody Team team) {
        return teamService.addTeam(team);
    }
    @PutMapping
    public String editTeam(@RequestBody Team team){
        return teamService.updateTeam(team);
    }
    @DeleteMapping("/{teamId}")
    public String deleteTeam(@PathVariable int teamId){
        return teamService.deleteTeam(teamId);
    }
    @GetMapping("/{teamId}/players")
    public List<Player> getPlayers(@PathVariable int teamId){
        return teamService.getPlayers(teamId);
    }
    @GetMapping("/ranking")
    public List<Team> ranking(){
        return teamService.ranking();
    }

}
