package com.ichings.generator.util;

/**
 * 标准错误信息
 *
 * @author 宋欢
 */
public interface Message {
    String SUCCESS = "OK";
    String FORBIDDEN = "无访问权限";
    String PAGE_NOT_FOUND = "页面不存在";
    String METHOD_NOT_ALLOWED = "请求方式不支持";
    String SYSTEM_RUN_ERR = "系统运行异常";
    String ARGS_ERR = "参数错误";
    String FILE_ERR = "文件错误";
    String DIRECTORY_ERR = "目录错误";
    String DB_ERR = "数据库错误";
    String UNKNOWN_ERR = "未知错误";
}
