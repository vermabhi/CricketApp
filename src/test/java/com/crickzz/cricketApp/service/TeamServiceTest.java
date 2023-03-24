package com.crickzz.cricketApp.service;

import com.crickzz.cricketApp.model.Player;
import com.crickzz.cricketApp.model.Team;
import com.crickzz.cricketApp.repository.MatchRepository;
import com.crickzz.cricketApp.repository.PlayerRepository;
import com.crickzz.cricketApp.repository.TeamRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {
    @Mock
    private PlayerRepository playerRepo;
    @Mock
    private MatchRepository matchRepo;
    @Mock
    private TeamRepository teamRepo;
    @InjectMocks
    private TeamService teamService;

    @InjectMocks
    private PlayerServiceTest playerServiceTest;

    @Test
    public void testGetAllTeam(){
        Mockito.when(teamRepo.findAll())
                .thenReturn((Collections.singletonList(mockTeam())));

        List<Team> teams = teamService.getAllTeam();
        Assertions.assertNotNull(teams);
        Assertions.assertEquals(1,teams.size());
    }

    @Test
    public void testGetTeamById(){
        Mockito.when(teamRepo.existsById(anyInt()))
                .thenReturn(true);
        Mockito.when(teamRepo.findByTeamId(1))
                .thenReturn(mockTeam());

        Team team = teamService.getTeamById(1);
        Assertions.assertNotNull(team);
        Assertions.assertEquals("T1",team.getTeamName());
    }

    @Test
    public void testGetTeamByIdFail(){
        Mockito.when(teamRepo.existsById(anyInt()))
                .thenReturn(false);

        Team team = teamService.getTeamById(1);
        Assertions.assertNull(team);
    }

    @Test
    public void testGetTeamByName(){
        Mockito.when(teamRepo.getTeamName(anyString()))
                .thenReturn(mockTeam());

        Team team = teamService.getTeamByName("T1");
        Assertions.assertNotNull(team);
        Assertions.assertEquals(1,team.getTeamId());
    }

    @Test
    public void testAddExistTeam(){
        Mockito.when(teamRepo.existsById(anyInt()))
                .thenReturn(true);

        String result = teamService.addTeam(mockTeam());
        Assertions.assertNotNull(result);
    }

    @Test
    public void testAddTeamSuccess(){
        Mockito.when(teamRepo.existsById(anyInt()))
                .thenReturn(false);
        Mockito.when(teamRepo.save(any()))
                .thenReturn(mockTeam());

        String result = teamService.addTeam(mockTeam());
        Assertions.assertNotNull(result);
    }

    @Test
    public void testUpdateTeamSuccess(){
        Mockito.when(teamRepo.existsById(anyInt()))
                .thenReturn(true);
        Mockito.when(teamRepo.findByTeamId(anyInt()))
                .thenReturn(mockTeam());

        String result = teamService.updateTeam(mockTeam());
        Assertions.assertNotNull(result);

    }

    @Test
    public void testUpdateTeamFail(){
        Mockito.when(teamRepo.existsById(anyInt()))
                .thenReturn(false);

        String result = teamService.updateTeam(mockTeam());
        Assertions.assertNotNull(result);

    }

    @Test
    public void testDeleteTeamSuccess(){
        Mockito.when(teamRepo.existsById(anyInt()))
                .thenReturn(true);

        String result = teamService.deleteTeam(1);
        Assertions.assertNotNull(result);
    }
    @Test
    public void testDeleteTeamFail(){
        Mockito.when(teamRepo.existsById(anyInt()))
                .thenReturn(false);

        String result = teamService.deleteTeam(1);
        Assertions.assertNotNull(result);
    }

    @Test
    public void testGetPlayers(){
        Mockito.when(playerRepo.findByTeamId(anyInt()))
                .thenReturn(Collections.singletonList(playerServiceTest.mockPlayer(1,1)));

        List<Player> players = teamService.getPlayers(1);
        Assertions.assertNotNull(players);
        Assertions.assertEquals(1,players.size());
    }

    @Test
    public void testRanking(){
        Mockito.when(teamRepo.findAll(Sort.by(Sort.Direction.DESC,"rating")))
                .thenReturn(Collections.singletonList(mockTeam()));

        List<Team> teams = teamService.ranking();
        Assertions.assertNotNull(teams);
        Assertions.assertEquals(1,teams.size());
    }


    protected Team mockTeam() {
        Team team = Team.builder().build();
        team.setTeamName("T1");
        team.setRating(123.123);
        team.setTeamId(1);
        return team;
    }
}
