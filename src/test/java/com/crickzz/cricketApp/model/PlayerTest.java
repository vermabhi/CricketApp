package com.crickzz.cricketApp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlayerTest {
    @Test
    public void testPlayer(){
        Player player = Player.builder().build();
        player.setPlayerName("P1");
        player.setPlayerId(1);
        player.setAge(21);
        player.setRole("batsman");
        player.setBattingStyle("left-handed");
        player.setBowlingStyle("right-arm-fast");
        player.setTeamId(1);


        Assertions.assertNotNull(player.getPlayerId());
        Assertions.assertNotNull(player.getPlayerName());
        Assertions.assertNotNull(player.getRole());
        Assertions.assertNotNull(player.getTeamId());
        Assertions.assertNotNull(player.getAge());
        Assertions.assertNotNull(player.getBattingStyle());
        Assertions.assertNotNull(player.getBowlingStyle());

    }
}
