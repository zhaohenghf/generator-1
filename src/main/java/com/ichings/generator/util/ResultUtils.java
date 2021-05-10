package com.ichings.generator.util;

/**
 * 构建 Data Transfer Object
 *
 * @author 宋欢
 */
public final class ResultUtils {

    private ResultUtils() {
    }

    /**
     * OK
     */
    public static <T> Result<T> toSuccess(T data) {
        Result<T> result = new Result<>();

        result.setCode(Errors.SUCCESS.getCode());
        result.setMessage(Errors.SUCCESS.getMessage());
        result.setData(data);

        return result;
    }

    /**
     * 结果转换
     */
    public static <T, S> Result<T> fromResult(Result<S> parameter) {
        Result<T> result = new Result<>();

        if (parameter != null) {
            result.setCode(parameter.getCode());
            result.setMessage(parameter.getMessage());
        }

        return result;
    }

    /**
     * 异常
     */
    public static <T> Result<T> fromException(BizException e) {
        Result<T> result = new Result<>();

        if (e != null) {
            result.setCode(e.getCode());
            result.setMessage(e.getMessage());
        }

        return result;
    }

    /**
     * 异常
     */
    public static <T> Result<T> fromException(NullPointerException e) {
        Result<T> result = new Result<>();

        if (e != null) {
            result.setCode(Errors.ARGS_ERR.getCode());
            result.setMessage(e.getMessage());
        }

        return result;
    }

    /**
     * 异常
     */
    public static <T> Result<T> fromException(IllegalArgumentException e) {
        Result<T> result = new Result<>();

        if (e != null) {
            result.setCode(Errors.ARGS_ERR.getCode());
            result.setMessage(e.getMessage());
        }

        return result;
    }

    /**
     * 异常
     */
    public static <T> Result<T> fromThrowable(Throwable tr) {
        Result<T> result = new Result<>();

        if (tr != null) {
            result.setMessage(tr.getMessage());
        }

        return result;
    }

    /**
     * 错误信息
     */
    public static <T> Result<T> fromMessage(String message) {
        return fromMessage(Errors.SYSTEM_RUN_ERR.getCode(), message);
    }

    /**
     * 错误码和错误信息
     */
    public static <T> Result<T> fromMessage(int code, String message) {
        Result<T> result = new Result<>();

        result.setCode(code);
        result.setMessage(message);

        return result;
    }

    /**
     * 错误接口
     */
    public static <T> Result<T> fromResult(IResult r) {
        return fromResult(r, null);
    }

    /**
     * 错误接口
     */
    public static <T> Result<T> fromResult(IResult r, String message) {
        Result<T> result = new Result<>();

        if (r != null) {
            result.setCode(r.getCode());
            result.setMessage(r.getMessage());
        }

        if (message != null) {
            result.setMessage(message);
        }

        return result;
    }

    /**
     * Data Transfer Object -> View Object
     * Result -> ResultSuccess
     * Result -> ResultSuccess.WithoutData
     * Result -> ResultError
     * Result -> ResultError.WithoutData
     */
    public static <T> Object dtoToVo(Result<T> result) {
        if (result == null) {
            return null;
        }

        T data = result.getData();

        if (result.isSuccess()) {
            return (data != null) ? new ResultSuccess<>(data) : new ResultSuccess.WithoutData();
        } else {
            int code = result.getCode();
            String message = result.getMessage();

            return (data != null) ? new ResultError<>(code, message, data) : new ResultError.WithoutData(code, message);
        }
    }

}
