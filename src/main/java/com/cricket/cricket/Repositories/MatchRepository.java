package com.cricket.cricket.Repositories;

import com.cricket.cricket.Entity.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository  extends MongoRepository<Match,Integer> {

}
