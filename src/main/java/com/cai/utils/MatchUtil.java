package com.cai.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by caibaolong on 2017/1/23.
 * 有关验证的工具
 */
public class MatchUtil {
    /**
     * 验证是否为正确的邮箱名
     *
     * @param mail 邮箱名
     * @return 是否
     */
    public static boolean isMail(String mail) {
        return mail.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
    }

    /**
     * 验证是否为正确的手机号
     *
     * @param phone 手机号
     * @return 是否
     */
    public static boolean isPhone(String phone) {
        return phone.matches("^(13[4,5,6,7,8,9]|15[0,8,9,1,7]|188|187)\\d{8}$");
    }

    /**
     * 验证是否为正确的身份证号
     *
     * @param idCard 身份证号
     * @return 如果正确则返回身份证号上的出生年月
     */
    public static String isIdCard(String idCard) {
        if (idCard.length()!=18) {
            return null;
        }
        String year = idCard.substring(6, 10);
        String month = idCard.substring(10, 12);
        String day = idCard.substring(12, 14);
        String yearMonthDay = new StringBuffer(year).append("-" + month).append("-" + day).toString();
        String reg = "(\\d{4}-(((0(1|3|5|7|8))|(1(0|2)))(-((0[1-9])|([1-2][0-9])|(3[0-1])))?)|(((0(2|4|6|9))|(11))(-((0[1-9])|([1-2][0-9])|(30)))?)|((02)(-((0[1-9])|(1[0-9])|(2[0-8])))?))|(((([0-9]{2})((0[48])|([2468][048])|([13579][26]))|(((0[48])|([2468][048])|([3579][26]))00)))-02-29)";
        if (yearMonthDay.matches(reg)) {
            return yearMonthDay;
        }
        return null;
    }
}
