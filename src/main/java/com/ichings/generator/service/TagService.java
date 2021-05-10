package com.ichings.generator.service;

import com.ichings.generator.db.TableSchema;
import com.ichings.generator.dto.CodeDto;
import com.ichings.generator.tag.TagValue;

import java.util.Map;

/**
 * 标签
 *
 * @author 宋欢
 */
public interface TagService {
    /**
     * 获取标签值
     *
     * @param dto    CodeDto
     * @param schema TableSchema
     * @return 数据，name => code
     */
    Map<String, String> getData(CodeDto dto, TableSchema schema);

    /**
     * 获取标签值
     *
     * @param dto    CodeDto
     * @param schema TableSchema
     * @return TagValue
     */
    TagValue getValue(CodeDto dto, TableSchema schema);

}
