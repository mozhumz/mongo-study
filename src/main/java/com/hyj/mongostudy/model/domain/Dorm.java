package com.hyj.mongostudy.model.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Dorm implements Serializable {
    private Long dormId;

    private String dormName;

    private String schoolAddress;

    private String collegeIdStr;

    private String collegeNameStr;

    private Long createTime;

    private Long updateTime;

    private Integer state;


}