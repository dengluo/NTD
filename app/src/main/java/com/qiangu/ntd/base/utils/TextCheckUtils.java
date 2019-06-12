package com.qiangu.ntd.base.utils;

import androidx.annotation.NonNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符校验工具类
 */
public class TextCheckUtils {

    /**
     * 判断手机格式是否正确
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile(
                "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    /**
     * 判断email格式是否正确
     */
    public static boolean isEmail(String email) {
        String str
                = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }


    /**
     * 判定单个字符是否是汉字
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS ||
                ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS ||
                ub ==
                        Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A ||
                ub == Character.UnicodeBlock.GENERAL_PUNCTUATION ||
                ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION ||
                ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }


    /**
     * 判定中文符号
     */
    public static boolean isChinesePuctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION ||
                ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION ||
                ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS ||
                ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS) {
            return true;
        }
        return false;
    }


    /**
     * 检测String是否全是中文
     */
    public static boolean checkNameChese(String name) {
        boolean res = true;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (!isChinese(cTemp[i])) {
                res = false;
                break;
            }
            if (isChinesePuctuation(cTemp[i])) {
                res = false;
                break;
            }
        }
        return res;
    }


    /**
     * 判断邮编，中国邮政编码为6位数字，第一位不为0
     */
    public static boolean isZipNO(String zipString) {
        String str = "^[1-9][0-9]{5}$";
        return Pattern.compile(str).matcher(zipString).matches();
    }


    private static boolean startCheck(String reg, String string) {
        boolean tem = false;
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
        tem = matcher.matches();
        return tem;
    }


    /**
     * 检验用户名
     * 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾
     * 用户名有最小长度和最大长度限制，比如用户名必须是4-20位
     */
    public static boolean checkUsername(String username, int min, int max) {
        String regex = "[\\w\u4e00-\u9fa5]{" + min + "," + max + "}(?<!_)";
        return startCheck(regex, username);
    }


    /**
     * 检验用户名
     * 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾
     * 有最小位数限制的用户名，比如：用户名最少为4位字符
     */
    public static boolean checkUsername(String username, int min) {
        // [\\w\u4e00-\u9fa5]{2,}?
        String regex = "[\\w\u4e00-\u9fa5]{" + min + ",}(?<!_)";
        return startCheck(regex, username);
    }


    /**
     * 检验用户名
     * 取值范围为a-z,A-Z,0-9,"_",汉字 最少一位字符，
     * 最大字符位数无限制，不能以"_"结尾
     */
    public static boolean checkUsername(String username) {
        String regex = "[\\w\u4e00-\u9fa5]+(?<!_)";
        return startCheck(regex, username);
    }


    /**
     * 得到加密后的电话号码
     */
    @NonNull public static String getEncryptNum(String userPhone) {
        return userPhone.substring(0, 3) + "****" +
                userPhone.substring(7, userPhone.length());
    }


    /**
     * 把一个字符串中的大写转为小写，小写转换为大写
     */
    public static String exChange(String str) {
        StringBuffer sb = new StringBuffer();
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (Character.isUpperCase(c)) {
                    sb.append(Character.toLowerCase(c));
                }
                else if (Character.isLowerCase(c)) {
                    sb.append(Character.toUpperCase(c));
                }
            }
        }

        return sb.toString();
    }


    /**
     * 把一个字符串中的大写转为小写
     */
    public static String exChangeLower(String str) {
        StringBuffer sb = new StringBuffer();
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (Character.isUpperCase(c)) {
                    sb.append(Character.toLowerCase(c));
                }
                if (Character.isUpperCase(c)) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }


    /**
     * 把一个字符串中的小写转为大写
     */
    public static String exChangeUpper(String str) {
        StringBuffer sb = new StringBuffer();
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (Character.isLowerCase(c)) {
                    sb.append(Character.toUpperCase(c));
                }
                else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }


    /**
     * 检验详细地址
     * 取值范围为a-z,A-Z,0-9,"_",汉字 最少一位字符，
     * 最大字符位数无限制，不能以"_"结尾
     */
    public static boolean checkDetailAddress(String address) {
        String regex = "[\\w\u4e00-\u9fa5]+(?<!_)";
        return startCheck(regex, address);
    }


    /**
     * 检验身份证
     *
     * @return 是合格身份证返回true, 否则返回false
     */
    public static boolean checkIdCard(String string) {
        String regex1 = "/^[1-9]{7}((0)|(1[0-2]))(([0|1|2])|3[0-1]){3}$/";
        //15位
        String regex2
                = "/^[1-9]{5}[1-9]{3}((0)|(1[0-2]))(([0|1|2])|3[0-1]){4}$/";
        //18位
        return startCheck(regex1, string) || startCheck(regex2, string);
    }


    /**
     * 检验护照
     * 护照号码的格式：
     * 因私普通护照号码格式有:14/15+7位数,G+8位数；因公普通的是:P.+7位数；
     * 公务的是：S.+7位数 或者 S+8位数,以D开头的是外交护照.D=diplomatic
     *
     * @return 是合格身护照返回true, 否则返回false
     */
    public static boolean checkPassport(String string) {
        String regex
                = "^1[45][0-9]{7}|G[0-9]{8}|P[0-9]{7}|S[0-9]{7,8}|D[0-9]+$";
        return startCheck(regex, string);
    }


    /**
     * 检验 港澳通行证验证
     *
     * @return 是合格港澳通行证验证返回true, 否则返回false
     */
    public static boolean checkHKMacao(String string) {
        String regex = "/^[HMhm]{1}([0-9]{10}|[0-9]{8})$/";
        return startCheck(regex, string);
    }


    /**
     * 检验 台湾通行证验证
     *
     * @return 是合格台湾通行证验证返回true, 否则返回false
     */
    public static boolean checkTaiwan(String string) {
        String regex1 = "/^[0-9]{8}$/";
        String regex2 = "/^[0-9]{10}$/";
        return startCheck(regex1, string) || startCheck(regex2, string);
    }


    /**
     * 判断是否全部为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


    /**
     * 判断是否全部为字母
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isAlphabet(String str) {
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        return pattern.matcher(str).matches();
    }


    /**
     * 判断是否全部为字母和数字组合
     *
     * @param str 传入的字符串
     * @return 是返回true, 否则返回false
     */
    public static boolean isAlpInt(String str) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        return pattern.matcher(str).matches();
    }


    ///**
    // * 银行卡号一般是16位或者19位。
    // * 由如下三部分构成。
    // * 1,前六位是：发行者标识代码
    // * 2,中间的位数是：个人账号标识（从卡号第七位开始），一般由6－12位数字组成。最多可以使用12位数字。
    // * 3,最后一位是:根据卡号前面的数字,采用Luhn算法计算出的最后一位校验位
    // */
    //public static boolean checkBankCard(String cardId) {
    //    if (cardId == null || cardId.trim().length() == 0) {
    //        return false;
    //    }
    //    if (cardId.length() != 16 || cardId.length() != 19) {
    //        return false;
    //    }
    //
    //    //根据Luhm法则得到校验位
    //    char bit = getBankCardCheckCode(
    //            cardId.substring(0, cardId.length() - 1));
    //    if (bit == 'N') {
    //        return false;
    //    }
    //
    //    //和银行卡号的校验位(最后一位)比较,相同为true 不同为false
    //    return cardId.charAt(cardId.length() - 1) == bit;
    //}
    //
    //
    ///**
    // * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
    // * 该校验的过程：
    // * 1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。
    // * 2、从卡号最后一位数字开始，逆向将偶数位数字(0、2、4等等)，先乘以2（如果乘积为两位数，则将其减去9或个位与十位相加的和），再求和。
    // * 3、将奇数位总和加上偶数位总和，结果应该可以被10整除。
    // */
    //public static char getBankCardCheckCode(String nonCheckCodeCardId) {
    //    //如果传的不是数字返回N
    //    if (nonCheckCodeCardId == null ||
    //            nonCheckCodeCardId.trim().length() == 0 ||
    //            !nonCheckCodeCardId.matches("\\d+")) {
    //        return 'N';
    //    }
    //    char[] chs = nonCheckCodeCardId.trim().toCharArray();
    //    int luhmSum = 0;
    //    /**
    //     * 注意是从下标为0处开始的
    //     */
    //    for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
    //        int k = chs[i] - '0';
    //        /**
    //         * 是偶数位数字做处理先乘以2（如果乘积为两位数，则将其减去9或个位与十位相加的和），再求和。
    //         * 不是则不做处理
    //         */
    //        if (j % 2 == 0) {
    //            k *= 2;
    //            k = k / 10 + k % 10;
    //        }
    //        luhmSum += k;
    //    }
    //    return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    //}




}
