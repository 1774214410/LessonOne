package com.liuzw.common.utils;

import org.springframework.util.StringUtils;

/**
 * 脱敏工具类
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/9 10:56
 */
public final class SensitiveInfoUtils {

    private SensitiveInfoUtils() {
        throw new AssertionError(" 不能产生实例");
    }

    /**
     * 名字脱敏处理
     * @param name 名字
     * @return 脱敏后的名字
     */
    public static String name(String name) {
        if (name == null || name.isEmpty()) { return "*"; }
        char[] chars = name.toCharArray();
        for (int i = 1; i < chars.length; i ++) {
            chars[i] = '*';
        }
        return new String(chars);
    }

    /**
     * 用户姓名脱敏
     * @param name 姓名
     * @return 脱敏后的姓名
     */
    public static String username(String name) {
        if (StringUtils.isEmpty(name) || name.length() == 1) { return "*"; }
        char[] chars = name.toCharArray();
        chars[1] = '*';
        for (int i = 2; i < chars.length - 1; i ++) {
            chars[i] = '*';
        }
        return new String(chars);
    }

    /**
     * 银行卡脱敏 （截取后4位）
     * @param cardNo 卡号
     * @return 脱敏后的卡号
     */
    public static String bankCard(String cardNo) {
        if (StringUtils.isEmpty(cardNo)) { return ""; }
        return cardNo.substring(cardNo.length() - 4);
    }

    /**
     * 手机号脱敏 （中间四位隐藏）
     * @param mobile 手机号
     * @return 脱敏后的手机号
     */
    public static String mobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) { return ""; }
        char[] chars = mobile.toCharArray();
        int i = 3;
        chars[i ++] = '*';
        chars[i ++] = '*';
        chars[i ++] = '*';
        chars[i] = '*';
        return new String(chars);
    }

    /**
     * 身份证脱敏
     * @param idNumber 身份证
     * @return 脱敏后的身份证
     */
    public static String idNumber(String idNumber) {
        if (StringUtils.isEmpty(idNumber)) { return ""; }
        char[] chars = idNumber.toCharArray();
        for (int i = 6; i < 14; i ++) {
            chars[i] = '*';
        }
        return new String(chars);
    }

    public static void main(String[] args){
        String name = mobile("13588439394");
        System.out.println(name);
    }
}
