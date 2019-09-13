package com.hyj.mongostudy.mapper.mongodb.mongo1;

/**
 * @author huyuanjia
 * @date 2019/4/28 17:15
 */

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : zhangmeng
 * Date : 2018/12/27 11:13
 * Description : 自定义的Spring data mongodb的Aggregation Operation
 */
public class RemoveDollarOperation implements AggregationOperation {
    /**
     * 查询结果新追加的列名
     */
    private String newField;

    /**
     * 需要关联的表中的外键
     */
    private String localField;

    public RemoveDollarOperation(String newField, String localField) {
        this.newField = newField;
        this.localField = localField;
    }

    @Override
    public Document toDocument(AggregationOperationContext context) {
        List<Object> eqObjects = new ArrayList<>();
        eqObjects.add(new Document("$substrCP", Arrays.asList("$$this.k", 0, 1)));
        eqObjects.add(new Document("$literal", "$"));

        List<Object> substrCPObjects = new ArrayList<>();
        substrCPObjects.add("$$this.k");
        substrCPObjects.add(1);
        substrCPObjects.add(new Document("$strLenCP", "$$this.k"));

        List<Object> objects = new ArrayList<>();
        objects.add(new Document("$eq", eqObjects));
        objects.add(new Document("$substrCP", substrCPObjects));
        objects.add("$$this.k");

        Document operation = new Document(
                "$addFields",
                new Document(newField,
                        new Document("$arrayToObject",
                                new Document("$map",
                                        new Document("input",new Document("$objectToArray", "$"+localField))
                                                .append("in", new Document("k",new Document("$cond", objects))
                                                        .append("v", "$$this.v")))
                        )
                )
        );

        return context.getMappedObject(operation);
    }
}

