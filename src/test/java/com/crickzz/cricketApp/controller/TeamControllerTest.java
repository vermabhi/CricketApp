package com.crickzz.cricketApp.controller;

import com.crickzz.cricketApp.model.Player;
import com.crickzz.cricketApp.model.Team;
import com.crickzz.cricketApp.service.TeamService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoTestRule;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
public class TeamControllerTest {

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @Test
    public void testGetAllTeams(){
        Mockito.when(teamService.getAllTeam())
                .thenReturn(Collections.singletonList(Team.builder().build()));

        List<Team> teams = teamController.getAllTeam();
        Assertions.assertNotNull(teams);
        Assertions.assertEquals(1,teams.size());
    }

    @Test
    public void testGetTeam(){
        Mockito.when(teamService.getTeamById(anyInt()))
                .thenReturn(Team.builder().build());
        Team team = teamController.getTeam(1);
        Assertions.assertNotNull(team);
    }

    @Test
    public void testGetTeamByName(){
        Mockito.when(teamService.getTeamByName(anyString()))
                .thenReturn(Team.builder().build());
        Team team = teamController.getTeam("T1");
        Assertions.assertNotNull(team);
    }

    @Test
    public void testAddTeam(){
        Mockito.when(teamService.addTeam(any()))
                .thenReturn("added");

        String result = teamController.addTeam(Team.builder().build());
        Assertions.assertEquals("added",result);

    }
    @Test
    public void testEditTeam(){
        Mockito.when(teamService.updateTeam(any()))
                .thenReturn("updated");

        String result = teamController.editTeam(Team.builder().build());
        Assertions.assertEquals("updated",result);

    }
    @Test
    public void testDeleteTeam(){
        Mockito.when(teamService.deleteTeam(anyInt()))
                .thenReturn("deleted");

        String result = teamController.deleteTeam(1);
        Assertions.assertEquals("deleted",result);
    }
    @Test
    public void testPlayers(){
        Mockito.when(teamService.getPlayers(anyInt()))
                .thenReturn(Collections.singletonList(Player.builder().build()));
        List<Player> players = teamController.getPlayers(1);
        Assertions.assertEquals(1,players.size());
    }

    @Test
    public void testRanking(){
        Mockito.when(teamService.ranking())
                .thenReturn(Collections.singletonList(Team.builder().build()));
        List<Team> teams = teamController.ranking();
        Assertions.assertEquals(1,teams.size());
    }
}
