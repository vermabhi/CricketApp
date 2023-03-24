package com.crickzz.cricketApp.service;

import com.crickzz.cricketApp.model.Match;
import com.crickzz.cricketApp.repository.MatchRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTest {
    @Mock
    private MatchRepository matchRepo;

    @InjectMocks
    private MatchService matchService;

    private Match match;

    public Match matchBuild(){
        match = Match.builder()
                .matchName("ODI")
                .playerId(1)
                .matches(31)
                .runs(12)
                .wickets(32)
                .hundreds(2)
                .fifties(3)
                .six(43)
                .fours(44)
                .build();
        return match;

    }

    @Test
    public void testAddMatch(){
        Mockito.when(matchRepo.save(any()))
                .thenReturn(matchBuild());

        String result = matchService.addMatch(match);
        Assertions.assertNotNull(result);
    }

    @Test
    public void testAddMatchEmpty(){
        String result = matchService.addMatch(null);
        Assertions.assertNotNull(result);
    }

}
