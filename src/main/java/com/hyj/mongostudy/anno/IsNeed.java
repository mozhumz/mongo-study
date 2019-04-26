package com.hyj.mongostudy.anno;

import java.lang.annotation.*;

/**
 * @author huyuanjia
 * 前端参数-是否必须
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IsNeed {
    boolean flag();
}
