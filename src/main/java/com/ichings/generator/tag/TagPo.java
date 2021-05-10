package com.ichings.generator.tag;

import com.ichings.generator.db.ColumnSchema;
import com.ichings.generator.db.TableSchema;
import com.ichings.generator.util.AssertUtils;

import java.util.List;

/**
 * Persistent Object 标签
 *
 * @author 宋欢
 */
public final class TagPo {
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
        setCriteriaFields(tagValue, columnList);
        setCriteriaMethods(tagValue, columnList);
        setMapperInsert(tagValue, columnList);
    }

    /**
     * ${poFields}
     */
    private static void setFields(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = PojoUtils.buildFields(PojoEnum.PO, columnList);
        tagValue.setPoFields(code);
    }

    /**
     * ${poMethods}
     */
    private static void setMethods(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = PojoUtils.buildMethods(PojoEnum.PO, columnList);
        tagValue.setPoMethods(code);
    }

    /**
     * ${poCriteriaFields}
     */
    private static void setCriteriaFields(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = PojoUtils.buildFields(PojoEnum.PO_CRITERIA, columnList);
        tagValue.setPoCriteriaFields(code);
    }

    /**
     * ${poCriteriaMethods}
     */
    private static void setCriteriaMethods(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = PojoUtils.buildMethods(PojoEnum.PO_CRITERIA, columnList);
        tagValue.setPoCriteriaMethods(code);
    }

    /**
     * ${poMapperInsert}
     */
    private static void setMapperInsert(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = PoUtils.buildMapperInsert(columnList);
        tagValue.setPoMapperInsert(code);
    }

}
