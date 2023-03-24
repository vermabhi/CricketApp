package com.crickzz.cricketApp.service;

import com.crickzz.cricketApp.model.Match;
import com.crickzz.cricketApp.model.Player;
import com.crickzz.cricketApp.model.Team;
import com.crickzz.cricketApp.repository.MatchRepository;
import com.crickzz.cricketApp.repository.PlayerRepository;
import com.crickzz.cricketApp.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    MatchRepository matchRepo;
    @Autowired
    TeamRepository teamRepo;

    @Autowired
    TeamService teamService;

    public List<Player> getAllPlayer() {
        return playerRepo.findAll();
    }

    public Player getPlayerById(int id) {
        return playerRepo.findByPlayerId(id);
    }

    public String addPlayer(Player player){
        int id = player.getPlayerId();
        long playersCount = playerRepo.countByTeamId(player.getTeamId());
        if(playersCount>=15)
            return "Team_id : "+player.getTeamId()+", Team size is full!!";
        if(playerRepo.existsById(id)){
            return "Player already exists with playerId : "+id;
        }
        playerRepo.save(player);
        return "Player added successfully with id :"+player.getPlayerId();
    }

    public String deletePlayer(int id){
        if(playerRepo.existsById(id)) {
            playerRepo.deleteById(id);
            return "Player having id " + id + " Deleted Successfully!!";
        }
        return "Player doesn't exists with player_id : "+id;
    }

    public String editPlayer(Player player){
        int id = player.getPlayerId();
        if(playerRepo.existsById(id)) {
            Player playerInfo = this.getPlayerById(id);
            playerInfo.setPlayerName(player.getPlayerName());
            playerInfo.setAge(player.getAge());
            playerInfo.setRole(player.getRole());
            playerInfo.setTeamId(player.getTeamId());
            playerInfo.setBowlingStyle(player.getBowlingStyle());
            playerInfo.setBattingStyle(player.getBattingStyle());
            playerRepo.save(playerInfo);
            return playerInfo.getPlayerName() + "'s info updated successfully";
        }
        return "Player doesn't exists with player_id : "+id;
    }

    public List<Player> getPlayerByName(String playerName) {
        return playerRepo.findByPlayerName(playerName);
    }

    public List<Match> stats(int playerId){
        if(playerRepo.existsById(playerId)) {
            return matchRepo.getStats(playerId);
        }
        return null;
    }
    public List<Match> matches(String matchName){
        return matchRepo.findByMatchName(matchName);
    }
    public List<?> ranking(String role, String matchName){
        if(role ==null || matchName == null)
            return null;
        List<Match> matches = matchRepo.findByMatchName(matchName);
        List<Map<?,?>> players = new ArrayList<>();
        matches.sort((Match o1, Match o2)-> {
                if(role.equals("batsman")) {
                    return (o1.getRuns() < o2.getRuns()) ? 1 : -1;
                }
                return (o1.getWickets() < o2.getWickets()) ? 1 : -1;
        });
        int rank =0;
        for(Match match: matches) {
            Map<String, Object> player = new HashMap<>();
            int playerId = match.getPlayerId();
            Player playerInfo = this.getPlayerById(playerId);
            Team team = teamRepo.findByTeamId(playerInfo.getTeamId());
            player.put("ranking",++rank);
            player.put("Match_type",match.getMatchName());
            player.put("player_id",playerId);
            player.put("player_name",playerInfo.getPlayerName());
            player.put("team_id",team.getTeamId());
            player.put("team_name",team.getTeamName());
            player.put("player_role",playerInfo.getRole());
            player.put("matches",match.getMatches());
            player.put("innings",match.getInnings());
            player.put("runs",match.getRuns());
            player.put("wickets",match.getWickets());
            players.add(player);
        }
        return players;
    }
}
