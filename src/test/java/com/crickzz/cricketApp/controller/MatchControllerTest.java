package com.crickzz.cricketApp.controller;

import com.crickzz.cricketApp.repository.MatchRepository;
import com.crickzz.cricketApp.service.MatchService;
import com.crickzz.cricketApp.service.MatchServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class MatchControllerTest {

    @Mock
    private MatchService matchService;
    @Mock
    private MatchServiceTest matchServiceTest;
    @InjectMocks
    private MatchController matchController;

    @Test
    public void testAddMatch(){
        Mockito.when(matchService.addMatch(any()))
                .thenReturn("Done");

        String result = matchController.addMatch(matchServiceTest.matchBuild());
        Assertions.assertNotNull(result);
    }
}
