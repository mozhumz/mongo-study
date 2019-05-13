//package com.hyj.mongostudy.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
///**
// * 第二个mongodb数据源
// * @author huyuanjia
// *
// */
//@Configuration
//@ConfigurationProperties(prefix="spring.data.mongodb.second")
//@EnableMongoRepositories(basePackages="com.hyj.mongostudy.mapper.mongodb.mongo2",
//mongoTemplateRef="secondaryMongoTemplate")
//public class SecondaryMongoConfig extends AbstractMongoConfig{
//
//	/**
//	 * 生成mongodb模板
//	 */
//    @Bean(name="secondaryMongoTemplate")
//    @Override
//    public MongoTemplate getMongoTemplate() {
//        return new MongoTemplate(mongoDbFactory());
//    }
//}
