package com.ichings.generator.db;

import com.ichings.generator.util.AssertUtils;
import com.ichings.generator.util.Check;
import com.ichings.generator.util.StringUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 综合描述
 *
 * @author 宋欢
 */
public final class DbUtils {
    /**
     * 唯一索引前缀
     */
    private static final String UNIQUE_KEY_PREV = "uk_";

    private DbUtils() {
    }

    /**
     * 获取全部表名
     */
    public static List<String> getTableNames(Connection conn) throws SQLException {
        AssertUtils.nonNull(conn, "conn");

        DatabaseMetaData metaData = conn.getMetaData();
        String dbName = conn.getCatalog();

        ResultSet rs = metaData.getTables(dbName, null, null, new String[]{"TABLE"});
        if (rs == null) {
            return null;
        }

        Set<String> result = new HashSet<>();

        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");
            if (Check.nonEmpty(tableName)) {
                result.add(tableName);
            }
        }

        return new ArrayList<>(result);
    }

    /**
     * 获取库的概要描述
     */
    public static DbSchema getSchema(Connection conn, List<String> tableNames) throws SQLException {
        AssertUtils.nonNull(conn, "conn");

        DatabaseMetaData metaData = conn.getMetaData();

        String dbName = conn.getCatalog();
        String productName = metaData.getDatabaseProductName();
        String productVersion = metaData.getDatabaseProductVersion();
        String driverName = metaData.getDriverName();
        String driverVersion = metaData.getDriverVersion();
        String url = metaData.getURL();
        String username = metaData.getUserName();
        boolean readonly = metaData.isReadOnly();
        boolean supportsBatchUpdates = metaData.supportsBatchUpdates();
        boolean supportsTransactions = metaData.supportsTransactions();
        List<TableSchema> tableList = getTable(metaData, dbName, tableNames);

        DbSchema.Builder builder = new DbSchema.Builder();

        builder.setName(dbName).
                setProductName(productName).
                setProductVersion(productVersion).
                setDriverName(driverName).
                setDriverVersion(driverVersion).
                setUrl(url).
                setUsername(username).
                setReadonly(readonly).
                setSupportsBatchUpdates(supportsBatchUpdates).
                setSupportsTransactions(supportsTransactions).
                setTableList(tableList);

        return builder.create();
    }

    /**
     * 获取表的概要描述
     */
    public static List<TableSchema> getTable(DatabaseMetaData metaData, String dbName, List<String> tableNameList) throws SQLException {
        if (Check.isEmpty(tableNameList)) {
            return null;
        }

        ResultSet rs = metaData.getTables(dbName, null, null, new String[]{"TABLE"});
        if (rs == null) {
            return null;
        }

        Set<String> tableNameSet = new HashSet<>();
        for (String tableName : tableNameList) {
            tableName = StringUtils.trimSpace(tableName);
            if (Check.nonEmpty(tableName)) {
                tableNameSet.add(tableName);
            }
        }

        List<TableSchema> result = new ArrayList<>();

        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");
            if (Check.isEmpty(tableName)) {
                continue;
            }

            if (!tableNameSet.contains(tableName)) {
                continue;
            }

            String remark = rs.getString("REMARKS");
            List<ColumnSchema> columns = getColumns(metaData, dbName, tableName);

            TableSchema t = (new TableSchema.Builder()).
                    setName(tableName).
                    setRemark(remark).
                    setColumns(columns).
                    create();

            result.add(t);
        }

        rs.close();
        return result;
    }

    /**
     * 获取列的概要描述列表
     */
    public static List<ColumnSchema> getColumns(DatabaseMetaData metaData, String dbName, String tableName) throws SQLException {
        List<String> primaryKeys = getPrimaryKeys(metaData, dbName, tableName);
        List<String> uniqueKeys = getUniqueKeys(metaData, dbName, tableName);
        return getColumns(metaData, dbName, tableName, primaryKeys, uniqueKeys);
    }

    /**
     * 获取列的概要描述列表
     */
    public static List<ColumnSchema> getColumns(DatabaseMetaData metaData, String dbName, String tableName, List<String> primaryKeys, List<String> uniqueKeys) throws SQLException {
        AssertUtils.nonNull(metaData, "metaData");
        AssertUtils.nonEmpty(dbName, "dbName");
        AssertUtils.nonEmpty(tableName, "tableName");

        ResultSet rs = metaData.getColumns(dbName, null, tableName, null);
        if (rs == null) {
            return null;
        }

        List<ColumnSchema> result = new ArrayList<>();

        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME");
            int type = rs.getInt("DATA_TYPE");
            String typeName = rs.getString("TYPE_NAME");
            int size = rs.getInt("COLUMN_SIZE");
            int scale = rs.getInt("DECIMAL_DIGITS");
            String defaultValue = rs.getString("COLUMN_DEF");
            boolean primaryKey = Check.nonNull(primaryKeys) && primaryKeys.contains(columnName);
            boolean uniqueKey = Check.nonNull(uniqueKeys) && uniqueKeys.contains(columnName);
            String allowNull = rs.getString("IS_NULLABLE");
            String autoIncrement = rs.getString("IS_AUTOINCREMENT");
            String remark = rs.getString("REMARKS");

            ColumnSchema.Builder builder = new ColumnSchema.Builder();

            ColumnSchema schema = builder.setName(columnName).
                    setType(type).
                    setTypeName(typeName).
                    setSize(size).
                    setScale(scale).
                    setDefaultValue(defaultValue).
                    setPrimaryKey(primaryKey).
                    setUniqueKey(uniqueKey).
                    setAllowNull("YES".equals(allowNull)).
                    setAutoIncrement("YES".equals(autoIncrement)).
                    setRemark(remark).
                    create();

            result.add(schema);
        }

        rs.close();
        return result;
    }

    /**
     * 获取主键名列表
     */
    public static List<String> getPrimaryKeys(DatabaseMetaData metaData, String dbName, String tableName) throws SQLException {
        AssertUtils.nonNull(metaData, "metaData");
        AssertUtils.nonEmpty(dbName, "dbName");
        AssertUtils.nonEmpty(tableName, "tableName");

        ResultSet rs = metaData.getPrimaryKeys(dbName, null, tableName);
        if (rs == null) {
            return null;
        }

        Set<String> result = new HashSet<>();

        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME");
            result.add(columnName);
        }

        rs.close();
        return new ArrayList<>(result);
    }

    /**
     * 获取唯一索引列表
     */
    public static List<String> getUniqueKeys(DatabaseMetaData metaData, String dbName, String tableName) throws SQLException {
        AssertUtils.nonNull(metaData, "metaData");
        AssertUtils.nonEmpty(dbName, "dbName");
        AssertUtils.nonEmpty(tableName, "tableName");

        ResultSet rs = metaData.getIndexInfo(dbName, null, tableName, false, false);
        if (rs == null) {
            return null;
        }

        Set<String> result = new HashSet<>();

        while (rs.next()) {
            String indexName = rs.getString("INDEX_NAME");
            if (Check.isEmpty(indexName)) {
                continue;
            }

            if (!indexName.contains(UNIQUE_KEY_PREV)) {
                continue;
            }

            indexName = indexName.replaceFirst(UNIQUE_KEY_PREV, "").toLowerCase();
            result.add(indexName);
        }

        rs.close();
        return new ArrayList<>(result);
    }

}
