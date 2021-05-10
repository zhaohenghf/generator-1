package com.ichings.generator.tag;

import com.ichings.generator.db.ColumnSchema;
import com.ichings.generator.db.TableSchema;
import com.ichings.generator.util.AssertUtils;

import java.util.List;

/**
 * Data Transfer Object 标签
 *
 * @author 宋欢
 */
public final class TagDto {
    /**
     * 设置标签值
     *
     * @param tagValue    TagValue
     * @param tableSchema TableSchema
     */
    public static void render(TagValue tagValue, TableSchema tableSchema) {
        AssertUtils.nonNull(tagValue, "tagValue");
        AssertUtils.nonNull(tableSchema, "tableSchema");

        List<ColumnSchema> columnList = tableSchema.getColumnList();
        setFields(tagValue, columnList);
        setMethods(tagValue, columnList);
    }

    /**
     * ${dtoFields}
     */
    private static void setFields(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = PojoUtils.buildFields(PojoEnum.DTO, columnList);
        tagValue.setDtoFields(code);
    }

    /**
     * ${dtoMethods}
     */
    private static void setMethods(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = PojoUtils.buildMethods(PojoEnum.DTO, columnList);
        tagValue.setDtoMethods(code);
    }

}
