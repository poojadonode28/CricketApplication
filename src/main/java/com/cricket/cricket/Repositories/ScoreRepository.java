package com.cricket.cricket.Repositories;

import com.cricket.cricket.Entity.ScoreInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScoreRepository extends MongoRepository<ScoreInfo,Integer> {
}
