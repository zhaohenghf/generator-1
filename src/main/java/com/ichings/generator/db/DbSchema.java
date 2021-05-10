package com.ichings.generator.db;

import com.ichings.generator.util.AssertUtils;
import com.ichings.generator.util.Check;
import com.ichings.generator.util.JsonParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 库的概要描述
 * 包含库名、库类型、库版本、驱动名、驱动版本、URL、用户名、表等
 *
 * @author 宋欢
 */
public final class DbSchema implements Serializable {
    /**
     * 库名，如：database
     */
    private String name;

    /**
     * 库类型，如：MySQL
     */
    private String productName;

    /**
     * 库版本，如：5.7.29
     */
    private String productVersion;

    /**
     * 驱动名，如：MySQL Connector/J
     */
    private String driverName;

    /**
     * 驱动版本，如：mysql-connector-java-8.0.20 (Revision: afc0a13cd3c5a0bf57eaa809ee0ee6df1fd5ac9b)
     */
    private String driverVersion;

    /**
     * 域名或Ip、数据库名、参数
     * 如：jdbc:mysql://127.0.0.1:3306/database?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&useAffectedRows=true&allowMultiQueries=true
     */
    private String url;

    /**
     * 用户名，如：root@172.18.0.1
     */
    private String username;

    /**
     * 只读？
     */
    private boolean readonly;

    /**
     * 支持批量更新？
     */
    private boolean supportsBatchUpdates;

    /**
     * 支持事务？
     */
    private boolean supportsTransactions;

    /**
     * 表名列表
     */
    private List<String> tableNames = new ArrayList<>();

    /**
     * 表的概要描述
     */
    private Map<String, TableSchema> tableMap = new HashMap<>();

    /**
     * 表的概要描述
     */
    private List<TableSchema> tableList = new ArrayList<>();

    /**
     * 存在？
     */
    public boolean hasTable(String name) {
        if (Check.isEmpty(name)) {
            return false;
        }

        return tableNames.contains(name);
    }

    @Override
    public String toString() {
        return JsonParser.toJson(this);
    }

    public String getName() {
        return name;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverVersion() {
        return driverVersion;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public boolean isSupportsBatchUpdates() {
        return supportsBatchUpdates;
    }

    public boolean isSupportsTransactions() {
        return supportsTransactions;
    }

    public List<String> getTableNames() {
        return tableNames;
    }

    public Map<String, TableSchema> getTableMap() {
        return tableMap;
    }

    public List<TableSchema> getTableList() {
        return tableList;
    }

    /**
     * Build a new {@link DbSchema}.
     */
    public static final class Builder {
        /**
         * 库名
         */
        private String name;

        /**
         * 库类型
         */
        private String productName;

        /**
         * 库版本
         */
        private String productVersion;

        /**
         * 驱动名
         */
        private String driverName;

        /**
         * 驱动版本
         */
        private String driverVersion;

        /**
         * 域名或Ip、数据库名、参数
         */
        private String url;

        /**
         * 用户名
         */
        private String username;

        /**
         * 只读？
         */
        private boolean readonly;

        /**
         * 支持批量更新？
         */
        private boolean supportsBatchUpdates;

        /**
         * 支持事务？
         */
        private boolean supportsTransactions;

        /**
         * 表的概要描述
         */
        private List<TableSchema> tableList;

        /**
         * 创建DbSchema对象
         *
         * @return a DbSchema Object
         */
        public DbSchema create() {
            AssertUtils.nonEmpty(name, "name");

            DbSchema schema = new DbSchema();

            schema.name = name;
            schema.productName = productName;
            schema.productVersion = productVersion;
            schema.driverName = driverName;
            schema.driverVersion = driverVersion;
            schema.url = url;
            schema.username = username;
            schema.readonly = readonly;
            schema.supportsBatchUpdates = supportsBatchUpdates;
            schema.supportsTransactions = supportsTransactions;

            if (Check.nonEmpty(tableList)) {
                for (TableSchema t : tableList) {
                    if (Check.isNull(t)) {
                        continue;
                    }

                    String name = t.getName();

                    schema.tableNames.add(name);
                    schema.tableMap.put(name, t);
                    schema.tableList.add(t);

                    schema.tableNames.add(name);
                }
            }

            return schema;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder setProductVersion(String productVersion) {
            this.productVersion = productVersion;
            return this;
        }

        public Builder setDriverName(String driverName) {
            this.driverName = driverName;
            return this;
        }

        public Builder setDriverVersion(String driverVersion) {
            this.driverVersion = driverVersion;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setReadonly(boolean readonly) {
            this.readonly = readonly;
            return this;
        }

        public Builder setSupportsBatchUpdates(boolean supportsBatchUpdates) {
            this.supportsBatchUpdates = supportsBatchUpdates;
            return this;
        }

        public Builder setSupportsTransactions(boolean supportsTransactions) {
            this.supportsTransactions = supportsTransactions;
            return this;
        }

        public Builder setTableList(List<TableSchema> tableList) {
            this.tableList = tableList;
            return this;
        }

    }

}
