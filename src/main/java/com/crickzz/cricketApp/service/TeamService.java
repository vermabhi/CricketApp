package com.crickzz.cricketApp.service;

import com.crickzz.cricketApp.model.Player;
import com.crickzz.cricketApp.model.Team;
import com.crickzz.cricketApp.repository.PlayerRepository;
import com.crickzz.cricketApp.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepo;

    @Autowired
    PlayerRepository playerRepo;

    public List<Team> getAllTeam() {
        return teamRepo.findAll();
    }

    public Team getTeamById(int teamId) {
        if(teamRepo.existsById(teamId)) {
            return teamRepo.findByTeamId(teamId);
        }
        return null;
    }

    public Team getTeamByName(String teamName) {
        return teamRepo.getTeamName(teamName);
    }

    public String addTeam(Team team) {
        int id = team.getTeamId();
        if(teamRepo.existsById(id))
            return "Team already exists with team_id : "+id;
        teamRepo.save(team);
        return team.getTeamName()+" team having team_id : "+team.getTeamId()+" added successfully!!";
    }

    public String updateTeam(Team team) {
        int id = team.getTeamId();
        if(teamRepo.existsById(id)){
            Team teamInfo = this.getTeamById(id);
            teamInfo.setTeamName(team.getTeamName());
            teamInfo.setRating(team.getRating());
            teamRepo.save(teamInfo);
            return teamInfo.getTeamName()+" team's Info updated successfully!!";
        }
        return "Team doesn't exists with team_id : "+id;
    }

    public String deleteTeam(int teamId) {
        if(teamRepo.existsById(teamId)) {
            teamRepo.deleteById(teamId);
            return teamId + " team deleted successfully!!";
        }
        return "Team doesn't exists with team_id : "+teamId;
    }

    public List<Player> getPlayers(int teamId){
        return playerRepo.findByTeamId(teamId);
    }

    public List<Team> ranking(){
        return teamRepo.findAll(Sort.by(Sort.Direction.DESC,"rating"));
    }
}
