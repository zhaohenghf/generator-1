package com.ichings.generator.tag;

import com.ichings.generator.db.ColumnSchema;
import com.ichings.generator.db.TableSchema;
import com.ichings.generator.util.AssertUtils;

import java.util.List;

/**
 * Repository 标签
 *
 * @author 宋欢
 */
public final class TagRepository {
    /**
     * 设置标签值
     *
     * @param tagValue    TagValue
     * @param tableSchema TableSchema
     */
    public static void render(TagValue tagValue, TableSchema tableSchema) {
        AssertUtils.nonNull(tagValue, "tagValue");
        AssertUtils.nonNull(tableSchema, "tableSchema");

        setCheckAndInitializeInsert(tagValue, tableSchema.getColumnList());
    }

    /**
     * ${repositoryCheckAndInitializeInsert}
     */
    private static void setCheckAndInitializeInsert(TagValue tagValue, List<ColumnSchema> columnList) {
        String code = RepositoryUtils.buildCheckAndInitializeInsert(columnList);
        tagValue.setRepositoryCheckAndInitializeInsert(code);
    }

}
