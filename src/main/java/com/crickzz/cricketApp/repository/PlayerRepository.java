package com.crickzz.cricketApp.repository;

import com.crickzz.cricketApp.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlayerRepository extends MongoRepository<Player,Integer> {
    List<Player> findByPlayerName(String playerName);

    List<Player> findByTeamId(int teamId);

    long countByTeamId(int teamId);

    Player findByPlayerId(int id);
}
