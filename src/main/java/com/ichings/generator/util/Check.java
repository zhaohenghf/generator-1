package com.ichings.generator.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * 校验
 *
 * @author 宋欢
 */
public final class Check {

    private Check() {
    }

    /**
     * 布尔值True？
     * not null and true
     */
    public static boolean isTrue(Boolean obj) {
        return obj != null && obj;
    }

    /**
     * 空对象？
     * null
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 非空对象？
     * not null
     */
    public static boolean nonNull(Object obj) {
        return obj != null;
    }

    /**
     * 空字符串？
     * null or ""
     *
     * <pre>
     * Check.isEmpty(null)      = true
     * Check.isEmpty("")        = true
     * Check.isEmpty(" ")       = false
     * Check.isEmpty("bob")     = false
     * Check.isEmpty("  bob  ") = false
     * </pre>
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 非空字符串？
     * not null and not empty ("")
     *
     * <pre>
     * Check.nonEmpty(null)      = false
     * Check.nonEmpty("")        = false
     * Check.nonEmpty(" ")       = true
     * Check.nonEmpty("bob")     = true
     * Check.nonEmpty("  bob  ") = true
     * </pre>
     */
    public static boolean nonEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    /**
     * 空集合？
     * null or empty
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 非空集合？
     * not null and not empty
     */
    public static boolean nonEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * 空Map？
     * null or empty
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 非空Map？
     * not null and not empty
     */
    public static boolean nonEmpty(Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    /**
     * 可转化？
     */
    public static boolean isInstance(Class<?> type, Object obj) {
        return type != null && type.isInstance(obj);
    }

    /**
     * 非零？
     * not null and unequal than 0
     */
    public static boolean nonZero(Integer num) {
        return num != null && num != 0;
    }

    /**
     * 非零？
     * not null and unequal than 0
     */
    public static boolean nonZero(Long num) {
        return num != null && num != 0L;
    }

    /**
     * 非零？
     * not null and unequal than 0
     */
    public static boolean nonZero(BigDecimal num) {
        return num != null && num.compareTo(BigDecimal.ZERO) != 0;
    }

    /**
     * 正数？
     * not null and greater than 0
     */
    public static boolean isPositive(Integer num) {
        return num != null && num > 0;
    }

    /**
     * 正数？
     * not null and greater than 0
     */
    public static boolean isPositive(Long num) {
        return num != null && num > 0L;
    }

    /**
     * 正数？
     * not null and greater than 0
     */
    public static boolean isPositive(BigDecimal num) {
        return num != null && num.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * 非负数？
     * not null and (greater or equal than 0)
     */
    public static boolean nonNegative(Integer num) {
        return num != null && num >= 0;
    }

    /**
     * 非负数？
     * not null and (greater or equal than 0)
     */
    public static boolean nonNegative(Long num) {
        return num != null && num >= 0L;
    }

    /**
     * 非负数？
     * not null and (greater or equal than 0)
     */
    public static boolean nonNegative(BigDecimal num) {
        return num != null && num.compareTo(BigDecimal.ZERO) > -1;
    }

}
