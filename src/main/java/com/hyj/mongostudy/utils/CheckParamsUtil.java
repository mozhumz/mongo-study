package com.hyj.mongostudy.utils;

import com.hyj.mongostudy.anno.IsNeed;
import com.hyj.mongostudy.exception.BaseException;
import com.hyj.mongostudy.exception.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 参数校验
 *
 * @author huyuanjia
 */
@Slf4j
public class CheckParamsUtil {
    /**
     * string判空
     *
     * @param params 参数数组
     * @return boolean boolean
     */
    public static boolean check(String... params) {
        for (String param : params) {
            if (param == null
                    || param.replaceAll("\\s*", "").equals("")
                    || param.equalsIgnoreCase("null")
                    || param.equalsIgnoreCase("undefined")) {
                return false;
            }
        }
        return true;
    }

    /**
     * 对象判空
     *
     * @param params params
     */
    public static void check(Object... params) {
        for (Object obj : params) {
            if (obj == null) {
                throw new BaseException(ErrorInfo.PARAMS_ERROR.desc);
            }
        }
    }

    /**
     * 列表判空
     *
     * @param params 参数数组
     */
    public static void checkList(List params) {
        if (params == null) {
            throw new BaseException(ErrorInfo.PARAMS_ERROR.desc);
        }
        for (Object param : params) {
            if (param == null) {
                throw new BaseException(ErrorInfo.PARAMS_ERROR.desc);
            }
        }
    }

    /**
     * 列表判空
     *
     * @param params 参数数组
     * @return boolean boolean
     */
    public static boolean checkList_boolean(List params) {
        if (params == null || params.isEmpty()) {
            return false;
        }

        for (Object param : params) {
            if (param == null) {
                return false;
            }
        }
        return true;
    }


    /**
     * string List判空
     *
     * @param params 参数数组
     * @return boolean boolean
     */
    public static boolean checkListStr(String... params) {
        for (String param : params) {
            if (param == null || param.trim().equals("") || param.equalsIgnoreCase("null")
                    || param.equalsIgnoreCase("undefined") || param.equals("[]")) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查实体类各个属性值是否为空
     *
     * @param obj 实体对象
     */
    public static void checkObj(Object obj) {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                IsNeed isNeed = field.getAnnotation(IsNeed.class);
                if (isNeed != null && isNeed.flag()) {
                    if (!check(field.get(obj) + "")) {
                        throw new BaseException(ErrorInfo.PARAMS_ERROR.desc);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                log.error("checkObj Exception" + e);
                throw new BaseException(ErrorInfo.SERVER_ERROR.desc);
            }
        }
    }

    public static boolean checkObj_boolean(Object obj) {
        if (obj == null) {
            return false;
        }
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {

            field.setAccessible(true);
            try {
                IsNeed isNeed = field.getAnnotation(IsNeed.class);
                if (isNeed != null && isNeed.flag()) {
                    if (!check(field.get(obj) + "")) {
                        return false;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                log.error("checkObj Exception" + e);
                throw new BaseException(ErrorInfo.SERVER_ERROR.desc);
            }
        }
        return true;
    }


    /**
     * 判断list是否有重复元素
     *
     * @param list
     * @return true 有重复
     */
    public static boolean checkRepeatListData(List list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new BaseException(ErrorInfo.EXCEL_EMPTY_ERR.desc);
        }
        Set set = new HashSet(list);
        return set.size() != list.size();

    }


}
