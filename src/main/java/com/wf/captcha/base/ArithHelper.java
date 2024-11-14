package com.wf.captcha.base;

import java.math.BigDecimal;

/**
 * 字符串计算器辅助类
 */
class ArithHelper {

    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 16;
    private static final String ONE = "1";

    // 这个类不能实例化
    private ArithHelper() {
    }

    // 将double转换为BigDecimal
    private static BigDecimal convertToBigDecimal(double value) {
        return new BigDecimal(Double.toString(value));
    }

    // 将String转换为BigDecimal
    private static BigDecimal convertToBigDecimal(String value) {
        return new BigDecimal(value);
    }

    /**
     * 提供精确的加法运算。
     */
    public static double add(double v1, double v2) {
        return convertToBigDecimal(v1).add(convertToBigDecimal(v2)).doubleValue();
    }

    public static double add(String v1, String v2) {
        return convertToBigDecimal(v1).add(convertToBigDecimal(v2)).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     */
    public static double sub(double v1, double v2) {
        return convertToBigDecimal(v1).subtract(convertToBigDecimal(v2)).doubleValue();
    }

    public static double sub(String v1, String v2) {
        return convertToBigDecimal(v1).subtract(convertToBigDecimal(v2)).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     */
    public static double mul(double v1, double v2) {
        return convertToBigDecimal(v1).multiply(convertToBigDecimal(v2)).doubleValue();
    }

    public static double mul(String v1, String v2) {
        return convertToBigDecimal(v1).multiply(convertToBigDecimal(v2)).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到小数点以后10位，以后的数字四舍五入。
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    public static double div(String v1, String v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算，由scale参数指定精度。
     */
    public static double div(double v1, double v2, int scale) {
        validateScale(scale);
        return convertToBigDecimal(v1).divide(convertToBigDecimal(v2), scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double div(String v1, String v2, int scale) {
        validateScale(scale);
        return convertToBigDecimal(v1).divide(convertToBigDecimal(v2), scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     */
    public static double round(double v, int scale) {
        validateScale(scale);
        return convertToBigDecimal(v).divide(convertToBigDecimal(ONE), scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double round(String v, int scale) {
        validateScale(scale);
        return convertToBigDecimal(v).divide(convertToBigDecimal(ONE), scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    // 校验scale参数
    private static void validateScale(int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
    }
}
