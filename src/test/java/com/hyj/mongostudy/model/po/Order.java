package com.hyj.mongostudy.model.po;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author huyuanjia
 * @date 2019/6/20 10:50
 */
@Document("t_order")
@Data
public class Order {
    private ObjectId id;
    private ObjectId userId;
    private Double money;
    private String produce;
    private Date createDate;
}
