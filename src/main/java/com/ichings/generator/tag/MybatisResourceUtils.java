package com.ichings.generator.tag;

import com.ichings.generator.db.ColumnSchema;
import com.ichings.generator.util.AssertUtils;
import com.ichings.generator.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis Resource 工具类
 *
 * @author 宋欢
 */
public final class MybatisResourceUtils {
    /**
     * 常用变量名
     * 如：
     * Object record = new Object();
     *
     * <foreach item="record">
     * userId = #{record.userId,jdbcType=BIGINT},
     */
    private static final String VARIABLE_NAME = "record";

    /**
     * ${mybatisResourceBaseResultMap}
     *
     * <id column="id" jdbcType="BIGINT" property="id"/>
     * <result column="user_id" jdbcType="BIGINT" property="userId"/>
     * ...
     */
    public static String buildBaseResultMap(List<ColumnSchema> list) {
        AssertUtils.nonNull(list, "list");

        List<String> result = new ArrayList<>();

        String leftTab = "\t\t";
        boolean firstLine = true;

        for (ColumnSchema c : list) {
            checkColumn(c);

            String name = c.getName();
            String jdbcName = c.getJdbcName();
            String fieldName = c.getCamelLowerFirst();
            String tag = c.isPrimaryKey() ? "id" : "result";

            String code = firstLine ? "" : leftTab;
            code += String.format("<%s column=\"%s\" jdbcType=\"%s\" property=\"%s\"/>", tag, name, jdbcName, fieldName);

            result.add(code);
            firstLine = false;
        }

        return StringUtils.join(result, "\n");
    }

    /**
     * ${mybatisResourceCriteria}
     * <pre>
     * <if test="userId != null">
     *     AND user_id = #{userId,jdbcType=BIGINT},
     * </if>
     * <if test="username != null">
     *     AND username = #{username,jdbcType=VARCHAR},
     * </if>
     * ...
     * </pre>
     */
    public static String buildCriteria(List<ColumnSchema> list) {
        AssertUtils.nonNull(list, "list");

        List<String> result = new ArrayList<>();

        String leftTab = "\t\t\t";
        boolean firstLine = true;

        for (ColumnSchema c : list) {
            checkColumn(c);

            String name = c.getName();
            String fieldName = c.getCamelLowerFirst();
            String jdbcName = c.getJdbcName();

            if (SqlIgnore.isIgnore(SqlIgnore.TYPE_WHERE, name)) {
                continue;
            }

            String code = firstLine ? "" : leftTab;
            code += String.format("<if test=\"%s != null\">\n", fieldName) +
                    leftTab + String.format("\t AND %s = #{%s,jdbcType=%s},\n", name, fieldName, jdbcName) +
                    leftTab + "</if>";

            result.add(code);
            firstLine = false;
        }

        return StringUtils.join(result, "\n");
    }

    /**
     * ${mybatisResourceInsertColumnList}
     * ${mybatisResourceInsertListColumnList}
     * <p>
     * user_id,
     * username,
     * ...
     */
    public static String buildInsertColumnList(List<ColumnSchema> list) {
        AssertUtils.nonNull(list, "list");

        List<String> result = new ArrayList<>();

        String leftTab = "\t\t";
        int line = 1;

        for (ColumnSchema c : list) {
            String name = c.getName();
            AssertUtils.nonEmpty(name, "name");

            if (SqlIgnore.isIgnore(SqlIgnore.TYPE_INSERT, name)) {
                continue;
            }

            String code = line == 1 ? "" : leftTab;
            code += name + ",";

            result.add(code);
            line++;
        }

        return StringUtils.trim(StringUtils.join(result, "\n"), ',');
    }

    /**
     * ${mybatisResourceInsertValueList}
     * ${mybatisResourceInsertListValueList}
     * <p>
     * #{userId,jdbcType=BIGINT},
     * #{record.username,jdbcType=VARCHAR},
     * ...
     */
    public static String buildInsertValueList(List<ColumnSchema> list, boolean foreach) {
        AssertUtils.nonNull(list, "list");

        List<String> result = new ArrayList<>();

        String leftTab = foreach ? "\t\t\t\t" : "\t\t";
        String variableName = foreach ? VARIABLE_NAME + "." : "";
        int line = 1;

        for (ColumnSchema c : list) {
            checkColumn(c);

            String name = c.getName();
            String fieldName = c.getCamelLowerFirst();
            String jdbcName = c.getJdbcName();

            if (SqlIgnore.isIgnore(SqlIgnore.TYPE_INSERT, name)) {
                continue;
            }

            String code = line == 1 ? "" : leftTab;
            code += String.format("#{%s%s,jdbcType=%s},", variableName, fieldName, jdbcName);

            result.add(code);
            line++;
        }

        return StringUtils.trim(StringUtils.join(result, "\n"), ',');
    }

    /**
     * ${mybatisResourceUpdateFieldList}
     * ${mybatisResourceUpdateListFieldList}
     * <pre>
     * <if test="userId != null">
     *     userId = #{userId,jdbcType=BIGINT},
     * </if>
     * <if test="username != null">
     *     username = #{record.username,jdbcType=VARCHAR},
     * </if>
     * ...
     * </pre>
     */
    public static String buildUpdateFieldList(List<ColumnSchema> list, boolean foreach) {
        AssertUtils.nonNull(list, "list");

        List<String> result = new ArrayList<>();

        String leftTab = foreach ? "\t\t\t\t\t" : "\t\t\t";
        boolean firstLine = true;

        for (ColumnSchema c : list) {
            checkColumn(c);

            String name = c.getName();
            String fieldName = c.getCamelLowerFirst();
            String jdbcName = c.getJdbcName();

            if (SqlIgnore.isIgnore(SqlIgnore.TYPE_UPDATE, name)) {
                continue;
            }

            if (foreach) {
                fieldName = VARIABLE_NAME + "." + fieldName;
            }

            String code = firstLine ? "" : leftTab;
            code += String.format("<if test=\"%s != null\">\n", fieldName) +
                    leftTab + String.format("\t%s = #{%s,jdbcType=%s},\n", name, fieldName, jdbcName) +
                    leftTab + "</if>";

            result.add(code);
            firstLine = false;
        }

        return StringUtils.join(result, "\n");
    }

    /**
     * 校验 ColumnSchema
     */
    private static void checkColumn(ColumnSchema column) {
        AssertUtils.nonNull(column, "column");

        AssertUtils.nonEmpty(column.getName(), "name");
        AssertUtils.nonEmpty(column.getCamelLowerFirst(), "camelLowerFirst");
        AssertUtils.nonEmpty(column.getJdbcName(), "jdbcName");
    }

}
