package com.ichings.generator.tag;

import com.ichings.generator.db.ColumnSchema;
import com.ichings.generator.db.TableSchema;
import com.ichings.generator.util.AssertUtils;

import java.util.List;

/**
 * View Object 标签
 *
 * @author 宋欢
 */
public final class TagVo {
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
        setReqCreateFields(tagValue, columnList);
        setReqCreateMethods(tagValue, columnList);
        setReqCriteriaFields(tagValue, columnList);
        setReqCriteriaMethods(tagValue, columnList);
        setReqUpdateFields(tagValue, columnList);
        setReqUpdateMethods(tagValue, columnList);
    }

    /**
     * ${voFields}
     */
    private static void setFields(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = PojoUtils.buildFields(PojoEnum.VO, columnList);
        tagValue.setVoFields(code);
    }

    /**
     * ${voMethods}
     */
    private static void setMethods(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = PojoUtils.buildMethods(PojoEnum.VO, columnList);
        tagValue.setVoMethods(code);
    }

    /**
     * ${voReqCreateFields}
     */
    private static void setReqCreateFields(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = PojoUtils.buildFields(PojoEnum.VO_CREATE, columnList);
        tagValue.setVoReqCreateFields(code);
    }

    /**
     * ${voReqCreateMethods}
     */
    private static void setReqCreateMethods(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = PojoUtils.buildMethods(PojoEnum.VO_CREATE, columnList);
        tagValue.setVoReqCreateMethods(code);
    }

    /**
     * ${voReqCriteriaFields}
     */
    private static void setReqCriteriaFields(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = PojoUtils.buildFields(PojoEnum.VO_CRITERIA, columnList);
        tagValue.setVoReqCriteriaFields(code);
    }

    /**
     * ${voReqCriteriaMethods}
     */
    private static void setReqCriteriaMethods(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = PojoUtils.buildMethods(PojoEnum.VO_CRITERIA, columnList);
        tagValue.setVoReqCriteriaMethods(code);
    }

    /**
     * ${voReqUpdateFields}
     */
    private static void setReqUpdateFields(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = PojoUtils.buildFields(PojoEnum.VO_UPDATE, columnList);
        tagValue.setVoReqUpdateFields(code);
    }

    /**
     * ${voReqUpdateMethods}
     */
    private static void setReqUpdateMethods(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = PojoUtils.buildMethods(PojoEnum.VO_UPDATE, columnList);
        tagValue.setVoReqUpdateMethods(code);
    }

}
