package com.cricket.cricket.Repositories;

import com.cricket.cricket.Entity.Inning;
import com.cricket.cricket.Entity.InningInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InningInfoRepository extends MongoRepository<InningInfo,Integer> {


}
