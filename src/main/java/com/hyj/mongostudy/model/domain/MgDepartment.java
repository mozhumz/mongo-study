package com.hyj.mongostudy.model.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author huyuanjia
 * @date 2019/4/28 16:54
 */
@Document("t_department")
@Data
public class MgDepartment {
    private String id;

    private String departmentName;

    @DBRef
    private MgCompany company;

    @DBRef
    private List<MgEmployee> employeeList;
}
