package com.ichings.generator.util;

import java.io.Serializable;

/**
 * View Object
 *
 * @author 宋欢
 */
public final class ResultError<T> implements Serializable {
    /**
     * 内容 = null
     */
    public static final class WithoutData implements Serializable {
        /**
         * 错误码，> 0
         */
        private final int code;

        /**
         * 错误信息，!ok
         */
        private final String message;

        public WithoutData(int code, String message) {
            this.code = code;
            this.message = Check.nonNull(message) ? message : "";
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

    }

    /**
     * 错误码，> 0
     */
    private final int code;

    /**
     * 错误信息，!ok
     */
    private final String message;

    /**
     * 内容
     */
    private final T data;

    public ResultError(int code, String message, T data) {
        this.code = code;
        this.message = Check.nonNull(message) ? message : "";
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}
