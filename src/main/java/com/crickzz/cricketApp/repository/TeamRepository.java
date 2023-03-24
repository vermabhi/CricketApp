package com.crickzz.cricketApp.repository;

import com.crickzz.cricketApp.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TeamRepository extends MongoRepository<Team,Integer> {
    @Query(value = "{teamName : ?0 }")
    Team getTeamName(String teamName);

    Team findByTeamId(int teamId);
}
