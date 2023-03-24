package com.crickzz.cricketApp.controller;

import com.crickzz.cricketApp.model.Match;
import com.crickzz.cricketApp.model.Player;
import com.crickzz.cricketApp.service.PlayerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PlayerControllerTest {
    @Mock
    private PlayerService playerService;
    @InjectMocks
    private PlayerController playerController;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPlayer(){
        Player p1 = Player.builder().build();
        Mockito.when(playerService.getAllPlayer())
                .thenReturn(Collections.singletonList(p1));

        List<Player> players = playerController.getAllPlayer();
        Assertions.assertNotNull(players);
        Assertions.assertEquals(1,players.size());
    }
    @Test
    public void testGetPlayerById(){
        Mockito.when(playerService.getPlayerById(anyInt()))
                .thenReturn(mockPlayer(1,1));

        Player player = playerController.getPlayer(1);
        Assertions.assertNotNull(player);
        Assertions.assertEquals(1,player.getTeamId());
    }

    @Test
    public void testGetPlayerByName(){
        Mockito.when(playerService.getPlayerByName(anyString()))
                .thenReturn(Collections.singletonList(mockPlayer(1,1)));

        List<Player> players = playerController.getPlayer("P1");
        Assertions.assertNotNull(players);
        Assertions.assertEquals(1,players.size());
    }

    @Test
    public void testAddPlayer(){
        Mockito.when(playerService.addPlayer(any()))
                .thenReturn("Added");
        String result = playerController.addPlayer(mockPlayer(1,1));
        Assertions.assertEquals("Added",result);
    }
    @Test
    public void testDeletePlayer(){
        Mockito.when(playerService.deletePlayer(anyInt()))
                .thenReturn("deleted");
        String result = playerController.deletePlayer(1);
        Assertions.assertEquals("deleted",result);
    }
    @Test
    public void testEditPlayer(){
        Mockito.when(playerService.editPlayer(any()))
                .thenReturn("edited");
        String result = playerController.editPlayer(mockPlayer(1,1));
        Assertions.assertEquals("edited",result);
    }

    @Test
    public void testPlayerStats(){
        Mockito.when(playerService.stats(anyInt()))
                .thenReturn(Collections.singletonList(Match.builder().build()));

        List<Match> matches = playerController.getPlayerStats(1);
        Assertions.assertEquals(1,matches.size());
    }
    @Test
    public void testMatches(){
        Mockito.when(playerService.matches(anyString()))
                .thenReturn(Collections.singletonList(Match.builder().build()));
        List<Match> matches = playerController.matchList("ODI");
        Assertions.assertEquals(1,matches.size());
    }

    @Test
    public void testranking(){
        Mockito.when(playerService.ranking(anyString(),anyString()))
                .thenReturn(new ArrayList<>());

        List<?> ranks= playerController.ranking("batsman", "ODI");
        Assertions.assertNotNull(ranks);
    }

    protected Player mockPlayer(int playerId, int teamId){
        return Player.builder()
                .playerName("P1")
                .playerId(playerId)
                .age(21)
                .role("batsman")
                .battingStyle("left-handed")
                .bowlingStyle("right-arm-fast")
                .teamId(teamId)
                .build();
    }

}
