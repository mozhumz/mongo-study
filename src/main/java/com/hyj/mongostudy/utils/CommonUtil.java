package com.hyj.mongostudy.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huyuanjia
 * @date 2018/9/18 16:49
 */
@Slf4j
public class CommonUtil {


    /**
     * 实体类转map
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                log.error("objectToMap failed" + e);
            }
        }
        return map;
    }

    /**
     * map转实体
     * @param map
     * @param beanClass
     * @return
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass)  {
        if (map == null)
            return null;
        Object obj=null;
        try {

            obj = beanClass.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                    continue;
                }

                field.setAccessible(true);
                if(map.get(field.getName())==null){
                    continue;
                }
                field.set(obj, map.get(field.getName()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return obj;
    }




    /**
     * 去除字符串空白（\s 可以匹配空格、制表符、换页符等空白字符）
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        if (str == null) {
            return str;
        }
        return str.replaceAll("\\s*", "");
    }

    /**
     * 将字符串数组转为list列表
     * @param listStr
     * @param object
     * @return
     */
    public static List getListByStr( String listStr, Class object) {
        if (CheckParamsUtil.checkListStr(listStr)) {
            net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(listStr);
            return  (List) net.sf.json.JSONArray.toCollection(jsonArray, object);
        }
        return null;
    }




}
