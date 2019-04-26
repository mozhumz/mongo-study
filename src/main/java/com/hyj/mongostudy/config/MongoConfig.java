package com.hyj.mongostudy.config;

/**
 * @author huyuanjia
 * @date 2019/4/26 11:15
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
class MongoConfig {

    /**
     * 开启mongodb事务
     * @param dbFactory
     * @return
     */
    @Bean
    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}
