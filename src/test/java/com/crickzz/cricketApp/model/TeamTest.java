package com.crickzz.cricketApp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TeamTest {
    @Test
    public void testTeam(){
        Team team = Team.builder().build();
        team.setTeamName("T1");
        team.setRating(123.123);
        team.setTeamId(1);

        Assertions.assertNotNull(team.getTeamId());
        Assertions.assertNotNull(team.getTeamName());
        Assertions.assertNotNull(team.getRating());
    }
}
