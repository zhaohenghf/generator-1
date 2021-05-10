package com.ichings.generator.service;

import com.ichings.generator.dto.CodeDto;

import java.io.IOException;
import java.util.Map;

/**
 * 生成代码
 *
 * @author 宋欢
 */
public interface CodeService {
    /**
     * 执行操作
     *
     * @param dto CodeDto
     */
    void execute(CodeDto dto);

    /**
     * 创建文件
     *
     * @param templateDir 模板目录（建议绝对路径）
     * @param targetDir   目标代码目录（建议绝对路径）
     * @param data        数据，name => code
     */
    void process(String templateDir, String targetDir, Map<String, String> data);

    /**
     * 从临时目录，复制到目标目录
     *
     * @param tmpDir    临时目录
     * @param targetDir 目标目录
     * @throws IOException 复制失败
     */
    void tmpToTarget(String tmpDir, String targetDir) throws IOException;

}
