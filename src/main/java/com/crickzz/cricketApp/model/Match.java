package com.crickzz.cricketApp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document(collection = "match")
public class Match{
    private String matchName;
    private int playerId;
    private int matches;
    private int innings;
    private int runs;
    private int wickets;
    private int hundreds;
    private int fifties;
    private int six;
    private int fours;
}
