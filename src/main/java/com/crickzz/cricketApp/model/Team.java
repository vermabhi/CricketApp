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
@Document(collection = "team")
public class Team {
    @Id
    private int teamId;
    private String teamName;
    private double rating;
}
