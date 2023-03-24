package com.crickzz.cricketApp.service;

import com.crickzz.cricketApp.model.Match;
import com.crickzz.cricketApp.model.Player;
import com.crickzz.cricketApp.model.Team;
import com.crickzz.cricketApp.repository.MatchRepository;
import com.crickzz.cricketApp.repository.PlayerRepository;
import com.crickzz.cricketApp.repository.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepo;
    @Mock
    private MatchRepository matchRepo;
    @Mock
    private TeamRepository teamRepo;

    @InjectMocks
    private PlayerService playerService;
    @InjectMocks
    private MatchServiceTest matchServiceTest;

    @InjectMocks
    private TeamServiceTest teamServiceTest;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPlayer(){
        Mockito.when(playerRepo.findAll())
                .thenReturn((Collections.singletonList(mockPlayer(1,1))));

        List<Player> players = playerService.getAllPlayer();
        Assertions.assertNotNull(players);
        Assertions.assertEquals(1,players.size());
    }

    @Test
    public void testAddPlayerSuccess(){
        Mockito.when(playerRepo.save(any()))
                .thenReturn(mockPlayer(1,1));
        String result1 = playerService.addPlayer(mockPlayer(1,1));
        Assertions.assertNotNull(result1);
    }

    @Test
    public void testAddPlayerInTeamFull(){
        Mockito.when(playerRepo.countByTeamId(anyInt()))
                .thenReturn(16L);
        String result1 = playerService.addPlayer(mockPlayer(1,1));
        Assertions.assertNotNull(result1);
    }

    @Test
    public void testAddExistPlayer(){
        Mockito.when(playerRepo.existsById(anyInt()))
                .thenReturn(true);
        String result1 = playerService.addPlayer(mockPlayer(1,1));
        Assertions.assertNotNull(result1);
    }

    @Test
    public void testGetPlayerByIdSuccess() {
        Mockito.when(playerRepo.findByPlayerId(1)).
                thenReturn(mockPlayer(1,1));
        Player player1 = null;
        player1 = playerService.getPlayerById(1);

        Assertions.assertNotNull(player1);
        Assertions.assertEquals(1,player1.getTeamId());
    }

    @Test
    public void testGetPlayerByNameEmpty() {
        Mockito.when(playerRepo.findByPlayerName(any()))
                .thenReturn(Collections.singletonList(mockPlayer(1,1)));

        List<Player> player1 = playerService.getPlayerByName("P1");
        Assertions.assertNotNull(player1);
    }

    @Test
    public void testDeletePlayerSuccess() {
        Mockito.when(playerRepo.existsById(anyInt()))
                .thenReturn(true);

        String result = playerService.deletePlayer(1);
        Assertions.assertNotNull(result);
    }

    @Test
    public void testDeletePlayerFail() {
        Mockito.when(playerRepo.existsById(anyInt()))
                .thenReturn(false);

        String result = playerService.deletePlayer(1);
        Assertions.assertNotNull(result);
    }

    @Test
    public void testEditPlayerSuccess(){
        Mockito.when(playerRepo.existsById(anyInt()))
                .thenReturn(true);
        Mockito.when(playerRepo.findByPlayerId(anyInt()))
                .thenReturn(mockPlayer(1,1));
        String result2 = playerService.editPlayer(mockPlayer(1,1));
        Assertions.assertNotNull(result2);
    }

    @Test
    public void testEditPlayerFail(){
        Mockito.when(playerRepo.existsById(anyInt()))
                .thenReturn(false);
        String result2 = playerService.editPlayer(mockPlayer(1,1));
        Assertions.assertNotNull(result2);
    }

    @Test
    public void testStatsSuccess(){
        Mockito.when(playerRepo.existsById(anyInt()))
                .thenReturn(true);
        List<Match> matches = playerService.stats(1);
        Assertions.assertNotNull(matches);
    }

    @Test
    public void testStatsEmpty(){
        Mockito.when(playerRepo.existsById(anyInt()))
                .thenReturn(false);
        List<Match> matches = playerService.stats(1);
        Assertions.assertNull(matches);
    }

    @Test
    public void testMatches(){
        Mockito.when(matchRepo.findByMatchName(anyString()))
                .thenReturn(anyList());

        List<Match> matches = playerService.matches("ODI");
        Assertions.assertNotNull(matches);
    }

    @Test
    public void testRankingBatsman(){
        Mockito.when(matchRepo.findByMatchName(anyString()))
                .thenReturn(Collections.singletonList(matchServiceTest.matchBuild()));
        Mockito.when(playerRepo.findByPlayerId(1))
                .thenReturn(mockPlayer(1,1));
        Mockito.when(teamRepo.findByTeamId(1))
                .thenReturn(teamServiceTest.mockTeam());

        List<?> result = playerService.ranking("batsman","ODI");
        Assertions.assertNotNull(result);
    }

    @Test
    public void testRankingBowler(){
        Mockito.when(matchRepo.findByMatchName(anyString()))
                .thenReturn(Collections.singletonList(matchServiceTest.matchBuild()));
        Mockito.when(playerRepo.findByPlayerId(1))
                .thenReturn(mockPlayer(1,1));
        Mockito.when(teamRepo.findByTeamId(1))
                .thenReturn(teamServiceTest.mockTeam());

        List<?> result = playerService.ranking("bowler","ODI");
        Assertions.assertNotNull(result);
    }

    @Test
    public void testRankingRoleNull(){
        List<?> result = playerService.ranking(null,"ODI");
        Assertions.assertNull(result);
    }

    @Test
    public void testRankingRoleMatchNull(){
        List<?> result = playerService.ranking(null,"OFI");
        Assertions.assertNull(result);
    }

    public Player mockPlayer(int playerId, int teamId){
        Player player = Player.builder()
                .playerName("P1")
                .playerId(playerId)
                .age(21)
                .role("batsman")
                .battingStyle("left-handed")
                .bowlingStyle("right-arm-fast")
                .teamId(teamId)
                .build();
        return player;
    }

}
