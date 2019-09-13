package com.hyj.mongostudy.model.po;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author huyuanjia
 * @date 2019/6/20 10:48
 */
@Document("t_user")
@Data
public class User {
    private ObjectId id;
    private String name;
    private Integer age;
    private Date createDate;
}
