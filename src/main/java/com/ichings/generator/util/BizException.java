package com.ichings.generator.util;

/**
 * 业务异常
 *
 * @author 宋欢
 */
public class BizException extends RuntimeException {
    /**
     * 错误码
     */
    private int code;

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(Errors err) {
        super(err.getMessage());
        this.code = err.getCode();
    }

    public BizException(IResult result) {
        super(result.getMessage());
        this.code = result.getCode();
    }

    public int getCode() {
        return code;
    }

}
