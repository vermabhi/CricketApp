package com.crickzz.cricketApp.controller;

import com.crickzz.cricketApp.model.Match;
import com.crickzz.cricketApp.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {
    @Autowired
    private MatchService matchService;
    @PostMapping
    public String addMatch(@RequestBody Match match){
        return matchService.addMatch(match);
    }
}
