package com.ichings.generator.tag;

import com.ichings.generator.db.ColumnSchema;
import com.ichings.generator.db.TableSchema;
import com.ichings.generator.util.AssertUtils;
import com.ichings.generator.util.Check;
import com.ichings.generator.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Unique 工具类
 *
 * @author 宋欢
 */
public final class UniqueUtils {
    /**
     * List<Template> -> Code
     */
    public static String getCodes(TableSchema tableSchema, String template, String leftTab) {
        AssertUtils.nonNull(tableSchema, "tableSchema");

        List<ColumnSchema> columns = tableSchema.getUniqueKeys();
        if (Check.isEmpty(columns)) {
            return "";
        }

        List<String> list = new ArrayList<>();
        for (ColumnSchema c : columns) {
            if (Check.nonNull(c)) {
                String code = getCode(tableSchema, c, template, leftTab);
                if (Check.nonEmpty(code)) {
                    list.add(code);
                }
            }
        }

        if (Check.nonEmpty(list)) {
            String code = StringUtils.join(list, "\n\n");
            if (Check.nonEmpty(code)) {
                if (code.startsWith(leftTab)) {
                    code = code.substring(leftTab.length());
                }

                return code;
            }
        }

        return "";
    }

    /**
     * Template -> Code
     */
    public static String getCode(TableSchema tableSchema, ColumnSchema columnSchema, String template, String leftTab) {
        AssertUtils.nonNull(tableSchema, "tableSchema");
        AssertUtils.nonNull(columnSchema, "columnSchema");
        AssertUtils.nonEmpty(template, "template");

        String tableNameUnderscoreLower = tableSchema.getUnderscoreLower();
        String columnRemark = columnSchema.getRemark();
        String columnName = columnSchema.getName();
        String columnNameCamelLowerFirst = columnSchema.getCamelLowerFirst();
        String columnNameCamelUpperFirst = columnSchema.getCamelUpperFirst();
        String columnNameUnderscoreUpper = columnSchema.getUnderscoreUpper();
        String columnNameHyphenLower = columnSchema.getHyphenLower();

        AssertUtils.nonEmpty(tableNameUnderscoreLower, "tableNameUnderscoreLower");
        AssertUtils.nonEmpty(columnRemark, "columnRemark");
        AssertUtils.nonEmpty(columnName, "columnName");
        AssertUtils.nonEmpty(columnNameCamelLowerFirst, "columnNameCamelLowerFirst");
        AssertUtils.nonEmpty(columnNameCamelUpperFirst, "columnNameCamelUpperFirst");
        AssertUtils.nonEmpty(columnNameUnderscoreUpper, "columnNameUnderscoreUpper");
        AssertUtils.nonEmpty(columnNameHyphenLower, "columnNameHyphenLower");

        return template.
                replaceAll(UniqueTag.LEFT_TAB, leftTab).
                replaceAll(UniqueTag.TABLE_NAME_UNDERSCORE_LOWER, tableNameUnderscoreLower).
                replaceAll(UniqueTag.COLUMN_REMARK, columnRemark).
                replaceAll(UniqueTag.COLUMN_NAME, columnName).
                replaceAll(UniqueTag.COLUMN_NAME_CAMEL_LOWER_FIRST, columnNameCamelLowerFirst).
                replaceAll(UniqueTag.COLUMN_NAME_CAMEL_UPPER_FIRST, columnNameCamelUpperFirst).
                replaceAll(UniqueTag.COLUMN_NAME_UNDERSCORE_UPPER, columnNameUnderscoreUpper).
                replaceAll(UniqueTag.COLUMN_NAME_HYPHEN_LOWER, columnNameHyphenLower);
    }

}
