package com.hyj.mongostudy.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.Data;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class AbstractMongoConfig{
    protected String host;
    protected int port;
    protected String username;
    protected String password;
    /**
     * 要访问的数据库
     */
    protected String database;
    
    /**
     * 鉴权数据库
     */
    protected String authenticationDatabase;
    
    /**
     * 鉴权并获取mongodb工厂类
     * @return
     */
    public MongoDbFactory mongoDbFactory(){
        ServerAddress serverAddress = new ServerAddress(host, port);
        List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
        credentialsList.add(MongoCredential.createCredential(username, authenticationDatabase, password.toCharArray()));
        return new SimpleMongoDbFactory(new MongoClient(serverAddress, credentialsList),database);
    }
    
    public abstract MongoTemplate getMongoTemplate();
}
