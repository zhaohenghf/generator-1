package com.ichings.generator.util;

/**
 * 标准错误码
 *
 * @author 宋欢
 */
public interface Code {
    /**
     * OK
     */
    int SUCCESS_NUM = 0;

    /**
     * -- 禁用，易误解成HttpStatus.OK --
     */
    int IGNORE_NUM = 200;

    /**
     * 无访问权限
     */
    int FORBIDDEN = 403;

    /**
     * 页面不存在
     */
    int PAGE_NOT_FOUND = 404;

    /**
     * 请求方式不支持
     */
    int METHOD_NOT_ALLOWED = 405;

    /**
     * 系统运行异常
     */
    int SYSTEM_RUN_ERR = 500;

    /**
     * 参数错误
     */
    int ARGS_ERR = 1001;

    /**
     * 文件错误
     */
    int FILE_ERR = 1101;

    /**
     * 目录错误
     */
    int DIRECTORY_ERR = 1201;

    /**
     * 数据库错误
     */
    int DB_ERR = 1301;

    /**
     * 未知错误
     */
    int UNKNOWN_ERR = 2008;

}
