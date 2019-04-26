package com.hyj.mongostudy.utils;



import com.hyj.mongostudy.exception.BaseException;
import com.hyj.mongostudy.exception.ErrorInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TimeUtil {
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");

    public static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM");

    public static final SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy");

    /**
     * 根据date获取时间字符串
     *
     * @param date date
     * @return String String
     */
    public static String getDateStr(Date date) {
        if (date == null) {
            return null;
        }
        return sdf.format(date);
    }

    /**
     * 根据字符串获取date
     *
     * @param str str
     * @return Date Date
     */
    public static Date getDate(String str, SimpleDateFormat sdf) {
        if (!CheckParamsUtil.check(str)) {
            return null;
        }

        try {
            Date date = sdf.parse(str);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(ErrorInfo.PARSE_TIME_ERROR.desc);
        }
    }

    public static Date getDate2(String str, SimpleDateFormat sdf) {
        if (!CheckParamsUtil.check(str)) {
            return null;
        }

        try {
            Date date = sdf.parse(str);
            return date;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 获取当前时间字符串
     *
     * @return str yyyy-MM-dd HH:mm:ss
     */
    public static String getNowDateStr() {
        return sdf.format(new Date());
    }

    /**
     * yyyy-MM-dd 10
     * yyyy-MM-dd HH:mm:ss 19
     *
     * @param dateStr
     * @return
     */
    public static String subDateStr(String dateStr, int len) {
        if (dateStr == null) {
            return null;
        }
        if (dateStr.length() > len) {

            return dateStr.substring(0, len);
        }
        return dateStr;
    }

    /**
     * 辅导员年度测评-获取学年列表
     *
     * @return
     */
    public static List<String> getSchoolYearList() {
        List<String> list = new ArrayList<>();
        //当前学年
        String nowYear = sdf4.format(new Date());
        int year = Integer.parseInt(nowYear);
        //获取四个学年列表
        int d=4;
        for (int i = 0; i < d; i++) {
			list.add((year-1-i+"-")+(year-i));
        }

        return list;
    }
    


}
