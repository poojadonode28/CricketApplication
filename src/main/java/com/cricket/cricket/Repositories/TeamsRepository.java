package com.cricket.cricket.Repositories;

import com.cricket.cricket.Entity.Inning;
import com.cricket.cricket.Entity.Player;
import com.cricket.cricket.Entity.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamsRepository extends MongoRepository<Team,Integer> {
    public Inning inning();
}
