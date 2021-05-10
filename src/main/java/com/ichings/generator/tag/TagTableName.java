package com.ichings.generator.tag;

import com.ichings.generator.db.TableSchema;
import com.ichings.generator.util.AssertUtils;

/**
 * Table Name 标签
 *
 * @author 宋欢
 */
public final class TagTableName {
    /**
     * 设置标签值
     * ${tableNameCamelLowerFirst}
     * ${tableNameCamelUpperFirst}
     * ${tableNameHyphenLower}
     * ${tableNameHyphenUpper}
     * ${tableNameUnderscoreLower}
     * ${tableNameUnderscoreUpper}
     *
     * @param tagValue    TagValue
     * @param tableSchema TableSchema
     */
    public static void render(TagValue tagValue, TableSchema tableSchema) {
        AssertUtils.nonNull(tagValue, "tagValue");
        AssertUtils.nonNull(tableSchema, "tableSchema");

        String tableName = tableSchema.getName();
        String camelLowerFirst = tableSchema.getCamelLowerFirst();
        String camelUpperFirst = tableSchema.getCamelUpperFirst();
        String hyphenLower = tableSchema.getHyphenLower();
        String hyphenUpper = tableSchema.getHyphenUpper();
        String underscoreLower = tableSchema.getUnderscoreLower();
        String underscoreUpper = tableSchema.getUnderscoreUpper();

        AssertUtils.nonEmpty(tableName, "tableName");
        AssertUtils.nonEmpty(camelLowerFirst, "camelLowerFirst");
        AssertUtils.nonEmpty(camelUpperFirst, "camelUpperFirst");
        AssertUtils.nonEmpty(hyphenLower, "hyphenLower");
        AssertUtils.nonEmpty(hyphenUpper, "hyphenUpper");
        AssertUtils.nonEmpty(underscoreLower, "underscoreLower");
        AssertUtils.nonEmpty(underscoreUpper, "underscoreUpper");

        tagValue.setTableName(tableName);
        tagValue.setTableNameCamelLowerFirst(camelLowerFirst);
        tagValue.setTableNameCamelUpperFirst(camelUpperFirst);
        tagValue.setTableNameHyphenLower(hyphenLower);
        tagValue.setTableNameHyphenUpper(hyphenUpper);
        tagValue.setTableNameUnderscoreLower(underscoreLower);
        tagValue.setTableNameUnderscoreUpper(underscoreUpper);
    }

}
