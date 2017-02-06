package com.cai.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by caibaolong on 2017/1/17.
 * 操作时间日期的相关工具
 */
public class TimeUtil {

    /**
     * 得到当前日期 格式为 2017-1-17
     *
     * @return 当前日期
     */
    public static String nowForYMD() {
        Date date = new Date(System.currentTimeMillis());
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 得到当前时间 格式为 2017-1-17 11:43:44
     *
     * @return 当前时间
     */
    public static String nowForYMDHMS() {
        Date date = new Date(System.currentTimeMillis());
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 比较开始日期和结束日期的大小
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 1, 开始日期小于结束日期. -1,开始日期大于结束日期 0, 2个日期相等
     * @throws ParseException 转化异常
     */
    public static int startEndCompare(String startDate, String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        return end.compareTo(start);
    }

    /**
     * 判断一个日期是否在另外2个日期中
     *
     * @param startDate 开始日期
     * @param midDate   中间日期
     * @param endDate   结束日期
     * @return 是否
     * @throws ParseException 转化异常
     */
    public static boolean midCompare(String startDate, String midDate, String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse(startDate);
        Date mid = sdf.parse(midDate);
        Date end = sdf.parse(endDate);
        if (start.compareTo(mid) < 1 && mid.compareTo(end) < 1) {
            return true;
        }
        return false;
    }

    /**
     * 转化从页面获取的时间格式 web: 2017-01-12T12:12 --> 2017-01-12 12:12:00
     *
     * @param webTime 页面获取的时间
     * @return 转好后时间
     * @throws ParseException 转化异常
     */
    public static String toFormat(String webTime) throws ParseException {
        return new StringBuffer(webTime.replace('T', ' ')).append(":00").toString();
    }

    /**
     * 转化为上班规定时间 (测试用) web: 2017-01-12T12:12 --> 2017-01-12 09:00:00
     *
     * @param webTime 页面获取的时间
     * @return 转好后时间
     * @throws ParseException 转化异常
     */
    public static String getWorkInTime(String webTime) throws ParseException {
        return new StringBuffer(webTime.substring(0, 10)).append(" 09:00:00").toString();
    }

    /**
     * 转化为下班规定时间 (测试用) web: 2017-01-12T12:12 --> 2017-01-12 18:00:00
     *
     * @param webTime 页面获取的时间
     * @return 转好后时间
     * @throws ParseException 转化异常
     */
    public static String getWorkOutTime(String webTime) throws ParseException {
        return new StringBuffer(webTime.substring(0, 10)).append(" 18:00:00").toString();
    }

    /**
     * 比较2个时间的大小
     *
     * @param t1 第 1个时间
     * @param t2 第 2个时间
     * @return [1] 第 1个时间早于第 2个时间. [-1]第 1个时间晚于第 2个时间 [0] 2个时间相等
     * @throws ParseException 转化异常
     */
    public static int timeCompare(String t1, String t2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(t2).compareTo(sdf.parse(t1));
    }

    /**
     * 在知道第1个时间大于第2个时间的情况下,算出差值(单位:整数小时)
     *
     * @param t1 第 1个时间
     * @param t2 第 2个时间
     * @return 相差的小时数 不满一小时也算一小时: 1.5小时算2小时
     * @throws ParseException 转化异常
     */
    public static int timeBalance(String t1, String t2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        long time1 = sdf.parse(t1.substring(11)).getTime() / 1000L / 60L;
        long time2 = sdf.parse(t2.substring(11)).getTime() / 1000L / 60L;
        double cha = (time1 - time2) / 60;
        int res = 0;
        if (time1 % 60 != 0) {
            res = (int) (Math.floor(cha) + 1);
        } else {
            res = (int) (Math.floor(cha));
        }
        return res;
    }

    /**
     * 通过当前年月得到上一个月的年月
     * 例子:2017-01返回2016-12 , 2017-02返回2017-01
     *
     * @param nowYM 当前年月或含年月的所有日期如:2017-01-XX XX:XX:XX...
     * @return 上一个月的年月
     */
    public static String getCountDate(String nowYM) {
        //获得年和月变转成int类型便于计算
        int year = Integer.parseInt(nowYM.substring(0, 4));
        int month = Integer.parseInt(nowYM.substring(5, 7));
        StringBuilder sb = new StringBuilder();
        if (month == 1) {
            sb.append(year - 1);
            sb.append("-12");
        } else {
            sb.append(year);
            if (month < 11) {
                sb.append("-0");
                sb.append(month - 1);
            } else {
                sb.append("-");
                sb.append(month - 1);
            }
        }
        return sb.toString();
    }

}
