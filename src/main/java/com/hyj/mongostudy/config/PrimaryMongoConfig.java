//package com.hyj.mongostudy.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
///**
// * 第一个mongodb数据源
// * @author huyuanjia
// *
// */
//@Configuration
//@ConfigurationProperties(prefix="spring.data.mongodb.primary" )
//@EnableMongoRepositories(basePackages = "com.hyj.mongostudy.mapper.mongodb.mongo1",
//mongoTemplateRef = "mongoTemplate")
//public class PrimaryMongoConfig extends AbstractMongoConfig{
//
//    /**
//     * 不需要密码，用这个
//     */
////    public MongoDbFactory mongoDbFactory(){
////        return new SimpleMongoDbFactory(new MongoClient(host, port), database);
////    }
//
//	/**
//	 * /**
//	 * 生成mongodb模板
//	 */
//    @Primary
//    @Bean(name="mongoTemplate")
//    @Override
//    public MongoTemplate getMongoTemplate() {
//        return new MongoTemplate(mongoDbFactory());
//    }
//
//
//}
