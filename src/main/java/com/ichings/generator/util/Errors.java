package com.ichings.generator.util;

/**
 * 标准错误枚举
 *
 * @author 宋欢
 */
public enum Errors implements IResult {
    /**
     * 错误码 => 错误信息
     */
    SUCCESS(Code.SUCCESS_NUM, Message.SUCCESS),
    FORBIDDEN(Code.FORBIDDEN, Message.FORBIDDEN),
    PAGE_NOT_FOUND(Code.PAGE_NOT_FOUND, Message.PAGE_NOT_FOUND),
    METHOD_NOT_ALLOWED(Code.METHOD_NOT_ALLOWED, Message.METHOD_NOT_ALLOWED),
    SYSTEM_RUN_ERR(Code.SYSTEM_RUN_ERR, Message.SYSTEM_RUN_ERR),
    ARGS_ERR(Code.ARGS_ERR, Message.ARGS_ERR),
    FILE_ERR(Code.FILE_ERR, Message.FILE_ERR),
    DIRECTORY_ERR(Code.DIRECTORY_ERR, Message.DIRECTORY_ERR),
    DB_ERR(Code.DB_ERR, Message.DB_ERR),
    UNKNOWN_ERR(Code.UNKNOWN_ERR, Message.UNKNOWN_ERR),

    ;

    /**
     * 错误码
     * 0：正确、> 0：错误、< 0：弃用
     */
    private int code;

    /**
     * 错误信息
     * ok：正确、!ok：错误
     */
    private String message;

    Errors(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
