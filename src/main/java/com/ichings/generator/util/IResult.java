package com.ichings.generator.util;

/**
 * 标准的数据传输接口
 *
 * @author 宋欢
 */
public interface IResult {
    /**
     * 错误码
     *
     * @return 0：正确、> 0：错误、< 0：弃用
     */
    int getCode();

    /**
     * 错误信息
     *
     * @return ok：正确、!ok：错误
     */
    String getMessage();

}
