package com.crickzz.cricketApp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document(collection = "player")
public class Player {
    @Id
    private int playerId;
    private int teamId;
    private String playerName;
    private int age;
    private String role;
    private String battingStyle;
    private String bowlingStyle;

}
