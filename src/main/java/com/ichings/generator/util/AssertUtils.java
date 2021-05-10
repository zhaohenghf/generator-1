package com.ichings.generator.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * 前提条件，标准提示
 *
 * @author 宋欢
 */
public final class AssertUtils {
    /**
     * 非空对象？
     * not null
     */
    public static final String NON_NULL = "%s can't be null";

    /**
     * 非空字符串？非空集合？
     * not null and not empty ("")
     * not null and not empty (Collection)
     * not null and not empty (Map)
     */
    public static final String NON_EMPTY = "%s can't be empty";

    /**
     * 非零？
     * not null and unequal than 0
     */
    public static final String NON_ZERO = "%s can't be equal than 0";

    /**
     * 正数？
     * not null and greater than 0
     */
    public static final String IS_POSITIVE = "%s must be greater than 0";

    /**
     * 非负数？
     * not null and (greater or equal than 0)
     */
    public static final String NON_NEGATIVE = "%s can't be less than 0";

    private AssertUtils() {
    }

    /**
     * 非空对象？not null
     * %s can't be null
     *
     * @throws NullPointerException if the object is null
     */
    public static void nonNull(Object obj, String name) {
        if (obj == null) {
            throw new NullPointerException(String.format(NON_NULL, name));
        }
    }

    /**
     * 非空字符串？not null and not empty ("")
     * %s can't be null
     * %s can't be empty
     *
     * @throws NullPointerException     if the str is null
     * @throws IllegalArgumentException if the str is empty
     */
    public static void nonEmpty(String str, String name) {
        nonNull(str, name);

        if (str.isEmpty()) {
            throw new IllegalArgumentException(String.format(NON_EMPTY, name));
        }
    }

    /**
     * 非空集合？not null and not empty
     * %s can't be null
     * %s can't be empty
     *
     * @throws NullPointerException     if the collection is null
     * @throws IllegalArgumentException if the collection is empty
     */
    public static void nonEmpty(Collection<?> collection, String name) {
        nonNull(collection, name);

        if (collection.isEmpty()) {
            throw new IllegalArgumentException(String.format(NON_EMPTY, name));
        }
    }

    /**
     * 非空Map？not null and not empty
     * %s can't be null
     * %s can't be empty
     *
     * @throws NullPointerException     if the map is null
     * @throws IllegalArgumentException if the map is empty
     */
    public static void nonEmpty(Map<?, ?> map, String name) {
        nonNull(map, name);

        if (map.isEmpty()) {
            throw new IllegalArgumentException(String.format(NON_EMPTY, name));
        }
    }

    /**
     * 非零？not null and unequal than 0
     * %s can't be null
     * %s can't be equal than 0
     *
     * @throws NullPointerException     if the num is null
     * @throws IllegalArgumentException if the num is equal than 0
     */
    public static void nonZero(Integer num, String name) {
        nonNull(num, name);

        if (num == 0) {
            throw new IllegalArgumentException(String.format(NON_ZERO, name));
        }
    }

    /**
     * 非零？not null and unequal than 0
     * %s can't be null
     * %s can't be equal than 0
     *
     * @throws NullPointerException     if the num is null
     * @throws IllegalArgumentException if the num is equal than 0
     */
    public static void nonZero(Long num, String name) {
        nonNull(num, name);

        if (num == 0L) {
            throw new IllegalArgumentException(String.format(NON_ZERO, name));
        }
    }

    /**
     * 非零？not null and unequal than 0
     * %s can't be null
     * %s can't be equal than 0
     *
     * @throws NullPointerException     if the num is null
     * @throws IllegalArgumentException if the num is equal than 0
     */
    public static void nonZero(BigDecimal num, String name) {
        nonNull(num, name);

        if (num.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException(String.format(NON_ZERO, name));
        }
    }

    /**
     * 正数？not null and greater than 0
     * %s can't be null
     * %s must be greater than 0
     *
     * @throws NullPointerException     if the num is null
     * @throws IllegalArgumentException if the num is less or equal than 0
     */
    public static void isPositive(Integer num, String name) {
        nonNull(num, name);

        if (num <= 0) {
            throw new IllegalArgumentException(String.format(IS_POSITIVE, name));
        }
    }

    /**
     * 正数？not null and greater than 0
     * %s can't be null
     * %s must be greater than 0
     *
     * @throws NullPointerException     if the num is null
     * @throws IllegalArgumentException if the num is less or equal than 0
     */
    public static void isPositive(Long num, String name) {
        nonNull(num, name);

        if (num <= 0L) {
            throw new IllegalArgumentException(String.format(IS_POSITIVE, name));
        }
    }

    /**
     * 正数？not null and greater than 0
     * %s can't be null
     * %s must be greater than 0
     *
     * @throws NullPointerException     if the num is null
     * @throws IllegalArgumentException if the num is less or equal than 0
     */
    public static void isPositive(BigDecimal num, String name) {
        nonNull(num, name);

        if (num.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(String.format(IS_POSITIVE, name));
        }
    }

    /**
     * 非负数？not null and (greater or equal than 0)
     * %s can't be null
     * %s can't be less than 0
     *
     * @throws NullPointerException     if the num is null
     * @throws IllegalArgumentException if the num is less than 0
     */
    public static void nonNegative(Integer num, String name) {
        nonNull(num, name);

        if (num < 0) {
            throw new IllegalArgumentException(String.format(NON_NEGATIVE, name));
        }
    }

    /**
     * 非负数？not null and (greater or equal than 0)
     * %s can't be null
     * %s can't be less than 0
     *
     * @throws NullPointerException     if the num is null
     * @throws IllegalArgumentException if the num is less than 0
     */
    public static void nonNegative(Long num, String name) {
        nonNull(num, name);

        if (num < 0L) {
            throw new IllegalArgumentException(String.format(NON_NEGATIVE, name));
        }
    }

    /**
     * 非负数？not null and (greater or equal than 0)
     * %s can't be null
     * %s can't be less than 0
     *
     * @throws NullPointerException     if the num is null
     * @throws IllegalArgumentException if the num is less than 0
     */
    public static void nonNegative(BigDecimal num, String name) {
        nonNull(num, name);

        if (num.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(String.format(NON_NEGATIVE, name));
        }
    }

}
