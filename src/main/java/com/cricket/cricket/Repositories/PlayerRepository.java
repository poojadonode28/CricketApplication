package com.cricket.cricket.Repositories;

import com.cricket.cricket.Entity.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends MongoRepository<Player,Integer> {

}
