package com.hyj.mongostudy.model.po;

/**
 * @author huyuanjia
 * @date 2019/4/2 17:10
 */
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 城市实体类
 *
 * Created by bysocket on 07/02/2017.
 */
@Data
@Document(collection = "t_city")
public class City extends org.bson.Document implements Serializable {

    private static final long serialVersionUID = -1L;

//    /**
//     * 城市编号
//     */
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;

    /**
     * 省份编号
     */
    private Long provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String description;


}
