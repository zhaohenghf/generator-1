package com.ichings.generator.util;

import java.io.Serializable;

/**
 * View Object
 *
 * @author 宋欢
 */
public final class ResultSuccess<T> implements Serializable {
    /**
     * 内容 = null
     */
    public static final class WithoutData implements Serializable {
        /**
         * 状态
         */
        private final boolean success = true;

        public boolean isSuccess() {
            return true;
        }

    }

    /**
     * 状态
     */
    private final boolean success = true;

    /**
     * 内容
     */
    private final T data;

    public ResultSuccess(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return true;
    }

    public T getData() {
        return data;
    }

}
