package com.yangfan.mvp.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yangfan
 * nrainyseason@163.com
 */

public class TimeUtils {
    /**
     * 获得当前时间
     * @return
     */
    public static String getSystemDate(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time) {
        SimpleDateFormat sf = null;
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString2(long time) {
        SimpleDateFormat sf = null;
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }
    /*自定义格式时间戳转换成字符窜*/
    public static String getDateToString3(long time,String format) {
        SimpleDateFormat sf = null;
        Date d = new Date(time);
        sf = new SimpleDateFormat(format);
        return sf.format(d);
    }

    /**
     * 日期转星期
     *
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate) {
        // 再转换为时间
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        //int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 获得几天前的时间
     * @return
     */
    public static String getRageDate(int days,String format){
        Calendar c = Calendar.getInstance(); // 当时的日期和时间
        int day; // 需要更改的天数
        day = c.get(Calendar.DAY_OF_MONTH) - days;
        c.set(Calendar.DAY_OF_MONTH, day);
        return getDateToString3(c.getTimeInMillis(),format);
    }
}
