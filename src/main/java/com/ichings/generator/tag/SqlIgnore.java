package com.ichings.generator.tag;

import com.ichings.generator.db.CommonColumn;
import com.ichings.generator.util.Check;

import java.util.ArrayList;
import java.util.List;

/**
 * 忽略字段
 *
 * @author 宋欢
 */
public final class SqlIgnore {
    /**
     * Insert
     */
    public static final int TYPE_INSERT = 1;

    /**
     * Update
     */
    public static final int TYPE_UPDATE = 2;

    /**
     * Where
     */
    public static final int TYPE_WHERE = 3;

    /**
     * ${mybatisResourceInsertColumnList}
     * ${mybatisResourceInsertListColumnList}
     * ${mybatisResourceInsertValueList}
     * ${mybatisResourceInsertListValueList}
     */
    private static final List<String> INSERT = new ArrayList<>();

    /**
     * ${mybatisResourceUpdateFieldList}
     * ${mybatisResourceUpdateListFieldList}
     */
    private static final List<String> UPDATE = new ArrayList<>();

    /**
     * ${mybatisResourceCriteria}
     */
    private static final List<String> WHERE = new ArrayList<>();

    static {
        INSERT.add(CommonColumn.CREATED_AT.getName());
        INSERT.add(CommonColumn.UPDATED_AT.getName());
        INSERT.add(CommonColumn.DELETED_ID.getName());
        INSERT.add(CommonColumn.DELETED_AT.getName());

        UPDATE.add(CommonColumn.ID.getName());
        UPDATE.add(CommonColumn.CREATED_AT.getName());
        UPDATE.add(CommonColumn.UPDATED_AT.getName());

        WHERE.add(CommonColumn.ID.getName());
        WHERE.add(CommonColumn.REMARK.getName());
        WHERE.add(CommonColumn.NOTE.getName());
        WHERE.add(CommonColumn.CREATED_AT.getName());
        WHERE.add(CommonColumn.UPDATED_AT.getName());
        WHERE.add(CommonColumn.DELETED_ID.getName());
        WHERE.add(CommonColumn.DELETED_AT.getName());
    }

    /**
     * 忽略字段？
     */
    public static boolean isIgnore(int type, String columnName) {
        if (Check.isEmpty(columnName)) {
            return false;
        }

        if (TYPE_INSERT == type) {
            return INSERT.contains(columnName);
        }

        if (TYPE_UPDATE == type) {
            return UPDATE.contains(columnName);
        }

        if (TYPE_WHERE == type) {
            return WHERE.contains(columnName);
        }

        return false;
    }

}
