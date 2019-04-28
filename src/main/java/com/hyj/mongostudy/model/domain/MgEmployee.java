package com.hyj.mongostudy.model.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author huyuanjia
 * @date 2019/4/28 16:55
 */
@Data
@Document("t_employee")
public class MgEmployee {
    private String id;

    private String employeeName;

    private String phone;

    @DBRef
    private MgDepartment department;
}
