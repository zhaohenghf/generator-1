package com.ichings.generator.util;

/**
 * 前提条件
 *
 * @author 宋欢
 */
public final class Assert {

    private Assert() {
    }

    /**
     * 非空对象？
     *
     * @throws NullPointerException if the object is null
     */
    public static void checkNonNull(Object obj, String message) {
        if (obj == null) {
            throw new NullPointerException(message);
        }
    }

    /**
     * 正确？
     *
     * @throws IllegalArgumentException if the expression is false
     */
    public static void checkArgument(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 正确？
     *
     * @throws IllegalStateException if the expression is false
     */
    public static void checkState(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

}
