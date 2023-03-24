package com.crickzz.cricketApp.service;

import com.crickzz.cricketApp.model.Match;
import com.crickzz.cricketApp.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    @Autowired
    MatchRepository matchRepo;

    public String addMatch(Match match){
        if(match != null) {
            matchRepo.save(match);
            return "Match info added successfully!!";
        } else {
            return "Request body empty provided!!";
        }
    }
}
