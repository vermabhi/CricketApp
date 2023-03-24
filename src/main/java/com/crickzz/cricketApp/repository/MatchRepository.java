package com.crickzz.cricketApp.repository;

import com.crickzz.cricketApp.model.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MatchRepository extends MongoRepository<Match,Integer> {
    @Query("{playerId : ?0}")
    List<Match> getStats(int playerId);

    List<Match> findByMatchName(String matchName);
}
