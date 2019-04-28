package com.hyj.mongostudy.model.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class DormManage implements Serializable {
    private Long id;

    private Long dormId;

    private String sn;

    private String name;


    private Integer type;

    private Long createTime;

    private Long updateTime;

    private Integer state;


}