package com.hyj.mongostudy.mapper.mongodb.mongo1;

import com.hyj.mongostudy.model.po.City;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author huyuanjia
 * @date 2019/4/26 11:24
 */
@Repository
public interface ICityMongoRepository extends MongoRepository<City,String> {
}
