package com.crickzz.cricketApp.controller;

import com.crickzz.cricketApp.model.Player;
import com.crickzz.cricketApp.repository.PlayerRepository;
import com.crickzz.cricketApp.service.PlayerService;
import com.crickzz.cricketApp.service.PlayerServiceTest;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.annotation.Collation;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;

@ExtendWith(MockitoExtension.class)
public class PlayerControllerTest {

    @Mock
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private PlayerServiceTest playerServiceTest;

    @InjectMocks
    private PlayerController playerController;

    @Test
    public void testGetAllPlayer(){
//        Mockito.when(playerService.getAllPlayer())
//                .thenCallRealMethod();
//        Mockito.when(playerRepository.findAll())
//                .thenReturn(Collections.singletonList(playerServiceTest.mockPlayer(1,1)));

        List<Player> players = playerController.getAllPlayer();
        Assertions.assertNotNull(players);

    }
    @Test
    public void testGetPlayerById(){
        Mockito.when(playerService.getPlayerById(anyInt()))
                .thenReturn(playerServiceTest.mockPlayer(1,1));

        Player player = playerController.getPlayer(1);
        Assertions.assertNotNull(player);
    }

}
