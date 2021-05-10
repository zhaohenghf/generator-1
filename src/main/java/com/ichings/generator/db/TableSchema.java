package com.ichings.generator.db;

import com.ichings.generator.util.Assert;
import com.ichings.generator.util.AssertUtils;
import com.ichings.generator.util.Check;
import com.ichings.generator.util.JsonParser;
import com.ichings.generator.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表的概要描述
 * 包含表名、备注、主键名、自增字段名、字段名、列默认值等
 *
 * @author 宋欢
 */
public final class TableSchema implements Serializable {
    /**
     * 原始表名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 主键列
     */
    private ColumnSchema primaryKey;

    /**
     * 唯一索引列表
     */
    private List<ColumnSchema> uniqueKeys = new ArrayList<>();

    /**
     * 字段名列表
     */
    private List<String> columnNames = new ArrayList<>();

    /**
     * 列默认值
     */
    private Map<String, String> attributeDefaults = new HashMap<>();

    /**
     * 列的概要描述
     */
    private Map<String, ColumnSchema> columnMap = new HashMap<>();

    /**
     * 列的概要描述
     */
    private List<ColumnSchema> columnList = new ArrayList<>();

    /**
     * 驼峰，首字母小写
     */
    private String camelLowerFirst;

    /**
     * 驼峰，首字母大写
     */
    private String camelUpperFirst;

    /**
     * 下划线，全小写
     */
    private String underscoreLower;

    /**
     * 下划线，全大写
     */
    private String underscoreUpper;

    /**
     * 连接线，全小写
     */
    private String hyphenLower;

    /**
     * 连接线，全大写
     */
    private String hyphenUpper;

    /**
     * 列名存在？
     */
    public boolean hasColumn(String name) {
        if (Check.isEmpty(name)) {
            return false;
        }

        return columnMap.containsKey(name);
    }

    @Override
    public String toString() {
        return JsonParser.toJson(this);
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }

    public ColumnSchema getPrimaryKey() {
        return primaryKey;
    }

    public List<ColumnSchema> getUniqueKeys() {
        return uniqueKeys;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public Map<String, String> getAttributeDefaults() {
        return attributeDefaults;
    }

    public Map<String, ColumnSchema> getColumnMap() {
        return columnMap;
    }

    public List<ColumnSchema> getColumnList() {
        return columnList;
    }

    public String getCamelLowerFirst() {
        return camelLowerFirst;
    }

    public String getCamelUpperFirst() {
        return camelUpperFirst;
    }

    public String getUnderscoreLower() {
        return underscoreLower;
    }

    public String getUnderscoreUpper() {
        return underscoreUpper;
    }

    public String getHyphenLower() {
        return hyphenLower;
    }

    public String getHyphenUpper() {
        return hyphenUpper;
    }

    /**
     * Build a new {@link TableSchema}.
     */
    public static final class Builder {
        /**
         * 只支持一个主键
         */
        public static String PRIMARY_KEY_LIMIT = "%s can only be 1 primary key";

        /**
         * 表名
         */
        private String name;

        /**
         * 备注
         */
        private String remark;

        /**
         * 列的概要描述
         */
        private List<ColumnSchema> columns;

        /**
         * 创建TableSchema对象
         *
         * @return a TableSchema Object
         */
        public TableSchema create() {
            AssertUtils.nonEmpty(name, "name");

            TableSchema schema = new TableSchema();

            schema.name = name;
            schema.remark = remark;

            schema.underscoreLower = StringUtils.hyphenCamelToUnderscore(name).toLowerCase();
            schema.underscoreUpper = schema.underscoreLower.toUpperCase();

            schema.hyphenLower = StringUtils.underscoreToHyphen(schema.underscoreLower);
            schema.hyphenUpper = schema.hyphenLower.toUpperCase();

            schema.camelLowerFirst = StringUtils.underscoreToCamel(schema.underscoreLower, false);
            schema.camelUpperFirst = StringUtils.underscoreToCamel(schema.underscoreLower, true);

            if (Check.isEmpty(columns)) {
                return schema;
            }

            for (ColumnSchema c : columns) {
                if (c == null) {
                    continue;
                }

                if (c.isPrimaryKey()) {
                    Assert.checkArgument(Check.isNull(schema.primaryKey), String.format(PRIMARY_KEY_LIMIT, schema.getName()));
                    schema.primaryKey = c;
                }

                if (c.isUniqueKey()) {
                    schema.uniqueKeys.add(c);
                }

                String columnName = c.getName();
                String defaultValue = c.getDefaultValue();

                schema.columnNames.add(columnName);

                if (Check.nonEmpty(defaultValue)) {
                    schema.attributeDefaults.put(columnName, defaultValue);
                }

                schema.columnMap.put(columnName, c);
                schema.columnList.add(c);
            }

            return schema;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRemark(String remark) {
            this.remark = remark;
            return this;
        }

        public Builder setColumns(List<ColumnSchema> columns) {
            this.columns = columns;
            return this;
        }

    }

}
