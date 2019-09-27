package com.fg.mvp_collection_demo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author kent
 */


public class DateUtil {
    public DateUtil() {
    }

    /**
     * 枚举日期格式
     */
    public enum DatePattern{
        /**
         * 格式:yyyy-MM-dd HH:mm:ss
         */
        ALL_TIME{
            public String getValue(){
                return "yyyy-MM-dd HH:mm:ss";
            }
        },
        /**
         * 格式:yyyy-MM-dd HH:mm
         */
        UNTIL_MINUTE{
            public String getValue(){
                return "yyyy-MM-dd HH:mm";
            }
        },
        /**
         * 格式:yyyy-MM-dd HH
         */
        UNTIL_HOUR{
            public String getValue(){
                return "yyyy-MM-dd HH";
            }
        },
        /**
         * 格式:yyyy-MM-dd
         */
        UNTIL_DAY{
            public String getValue(){
                return "yyyy-MM-dd";
            }
        },
        /**
         * 格式:yyyy-MM
         */
        UNTIL_MONTH{
            public String getValue(){
                return "yyyy-MM";
            }
        },
        /**
         * 格式:yyyy
         */
        ONLY_YEAR{
            public String getValue(){
                return "yyyy";
            }
        },
        /**
         * 格式:HH:mm:ss
         */
        ONLY_TIME{
            public String getValue(){
                return "HH:mm:ss";
            }
        },
        /**
         * 格式:HH:mm:ss
         */
        ONLY_HOUR_MINUTE{
            public String getValue(){
                return "HH:mm";
            }
        };
        public abstract String getValue();
    }

    /**
     * 获取当前时间
     * @param datePattern
     * @return
     */
    public static String getcurTime(DatePattern datePattern)
    {
        String dateString="";
        try {
            Calendar calendar=Calendar.getInstance();
            Date date=calendar.getTime();
            SimpleDateFormat sdf=new SimpleDateFormat(datePattern.getValue(),Locale.CHINA);
            dateString=sdf.format(date);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return dateString;
    }
    /**
     * 将字符串转换成Date对象
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date stringToDate(String dateString,DatePattern pattern)
    {
        Date date=null;
        try{
            SimpleDateFormat sdf=new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
            date=sdf.parse(dateString);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 将日期转成字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToString(Date date,DatePattern pattern)
    {
        String dateString="";
        try{
            SimpleDateFormat sdf=new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
            dateString=sdf.format(date);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return dateString;
    }
    /**
     * 获取当前日期
     * @return
     */
    public static int getCurDate(){
        Calendar calendar=Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }
    /**
     * 获取当前年份
     * @return
     */
    public static int getCurYear(){
        Calendar calendar=Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }
    /**
     * 获取当前月份
     * @return
     */
    public static int getCurMonth(){
        Calendar calendar=Calendar.getInstance();
        return calendar.get(Calendar.MONTH)+1;
    }
    /**
     * 获取本月的天数
     * @return
     */
    public static int getDaysOfCurMonth(){
        Calendar calendar=Calendar.getInstance();
        return daysOfMonth((calendar.get(Calendar.YEAR)),calendar.get(Calendar.DATE)+1);
    }
    /**
     *
     *  获取指定月份的天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int daysOfMonth(int year, int month) {
        switch (month) {
            case 1:
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    return 29;
                } else {
                    return 28;
                }
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return 30;
            case 12:
                return 31;
            default:
                return -1;
        }
    }

    /**
     * 获取指定日期 星期几
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (weekIndex < 0) weekIndex = 0;
        return weekDays[weekIndex];
    }

    /**
     * 获取指定日期的周系列号
     *
     * @param date
     * @return
     */
    public static int getWeekIndexOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK);
        if (index == 1) return 7;
        return --index;
    }
}