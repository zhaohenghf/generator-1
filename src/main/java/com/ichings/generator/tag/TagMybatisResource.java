package com.ichings.generator.tag;

import com.ichings.generator.db.ColumnSchema;
import com.ichings.generator.db.TableSchema;
import com.ichings.generator.util.AssertUtils;
import com.ichings.generator.util.StringUtils;

import java.util.List;

/**
 * Mybatis Resource 标签
 *
 * @author 宋欢
 */
public final class TagMybatisResource {
    /**
     * 设置标签值
     *
     * @param tagValue    TagValue
     * @param tableSchema TableSchema
     */
    public static void render(TagValue tagValue, TableSchema tableSchema) {
        AssertUtils.nonNull(tagValue, "tagValue");
        AssertUtils.nonNull(tableSchema, "tableSchema");

        List<String> columnNames = tableSchema.getColumnNames();
        List<ColumnSchema> columnList = tableSchema.getColumnList();

        setBaseResultMap(tagValue, columnList);
        setBaseColumnList(tagValue, columnNames);
        setCriteria(tagValue, columnList);
        setInsertColumnList(tagValue, columnList);
        setInsertValueList(tagValue, columnList);
        setInsertListColumnList(tagValue, columnList);
        setInsertListValueList(tagValue, columnList);
        setUpdateFieldList(tagValue, columnList);
        setUpdateListFieldList(tagValue, columnList);
    }

    /**
     * ${mybatisResourceBaseResultMap}
     *
     * <id column="id" jdbcType="BIGINT" property="id"/>
     * <result column="user_id" jdbcType="BIGINT" property="userId"/>
     * ...
     */
    private static void setBaseResultMap(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = MybatisResourceUtils.buildBaseResultMap(columnList);
        tagValue.setMybatisResourceBaseResultMap(code);
    }

    /**
     * ${mybatisResourceBaseColumnList}
     *
     * <p>
     * id,
     * username,
     * ...
     */
    private static void setBaseColumnList(TagValue tagValue, List<String> columnNames) {
        AssertUtils.nonNull(columnNames, "columnNames");

        String code = StringUtils.join(columnNames, ",\n\t\t");
        tagValue.setMybatisResourceBaseColumnList(code);
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
    private static void setCriteria(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = MybatisResourceUtils.buildCriteria(columnList);
        tagValue.setMybatisResourceCriteria(code);
    }

    /**
     * ${mybatisResourceInsertColumnList}
     */
    private static void setInsertColumnList(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = MybatisResourceUtils.buildInsertColumnList(columnList);
        tagValue.setMybatisResourceInsertColumnList(code);
    }

    /**
     * ${mybatisResourceInsertValueList}
     */
    private static void setInsertValueList(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = MybatisResourceUtils.buildInsertValueList(columnList, false);
        tagValue.setMybatisResourceInsertValueList(code);
    }

    /**
     * ${mybatisResourceInsertListColumnList}
     */
    private static void setInsertListColumnList(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = MybatisResourceUtils.buildInsertColumnList(columnList);
        tagValue.setMybatisResourceInsertListColumnList(code);
    }

    /**
     * ${mybatisResourceInsertListValueList}
     */
    private static void setInsertListValueList(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = MybatisResourceUtils.buildInsertValueList(columnList, true);
        tagValue.setMybatisResourceInsertListValueList(code);
    }

    /**
     * ${mybatisResourceUpdateFieldList}
     */
    private static void setUpdateFieldList(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = MybatisResourceUtils.buildUpdateFieldList(columnList, false);
        tagValue.setMybatisResourceUpdateFieldList(code);
    }

    /**
     * ${mybatisResourceUpdateListFieldList}
     */
    private static void setUpdateListFieldList(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = MybatisResourceUtils.buildUpdateFieldList(columnList, true);
        tagValue.setMybatisResourceUpdateListFieldList(code);
    }

}
