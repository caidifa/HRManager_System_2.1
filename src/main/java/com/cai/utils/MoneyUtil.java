package com.cai.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by caibaolong on 2017/1/17.
 * 操作数字金钱的相关工具
 */
public class MoneyUtil {

    /**
     * 对double保留2位小数
     *
     * @return
     */
    public static double saveTwoNumber(double d) {
        return Double.parseDouble(new DecimalFormat("######0.00").format(d));
    }




}
