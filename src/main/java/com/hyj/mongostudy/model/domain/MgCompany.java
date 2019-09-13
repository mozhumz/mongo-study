package com.hyj.mongostudy.model.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author huyuanjia
 * @date 2019/4/28 16:53
 */
@Data
@Document("t_company")
public class MgCompany {
    private String id;

    private String companyName;

    private String mobile;
}
