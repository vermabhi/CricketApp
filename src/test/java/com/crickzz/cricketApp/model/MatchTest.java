package com.crickzz.cricketApp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MatchTest {
    @Test
    public void testMatch(){
        Match match = Match.builder().build();
        match.setMatchName("ODI");
        match.setPlayerId(1);
        match.setMatches(31);
        match.setInnings(54);
        match.setRuns(12);
        match.setWickets(32);
        match.setHundreds(2);
        match.setFifties(3);
        match.setSix(43);
        match.setFours(44);

        Assertions.assertEquals(match.getMatches(),31);
        Assertions.assertEquals(match.getRuns(),12);
        Assertions.assertEquals(match.getMatchName(),"ODI");
        Assertions.assertEquals(match.getWickets(),32);
        Assertions.assertEquals(match.getFifties(),3);
        Assertions.assertEquals(match.getSix(),43);
        Assertions.assertEquals(match.getFours(),44);
        Assertions.assertEquals(match.getHundreds(),2);
        Assertions.assertEquals(match.getPlayerId(),1);
        Assertions.assertEquals(match.getInnings(),54);

    }
}
