package com.cai.utils;

import java.text.DecimalFormat;

/**
 * Created by caibaolong on 2017/1/17.
 * 操作字符串的相关工具
 */
public class StringUtil {

    /**
     * 合并2个字符串
     *
     * @param s1 在前面的字符串
     * @param s2 在后面的字符串
     * @return 一个合并好的字符串
     */
    public static String toOne(String s1, String s2) {
        return new StringBuffer(s1).append(s2).toString();
    }


}
