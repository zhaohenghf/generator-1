package com.ichings.generator.db;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;

/**
 * 类型枚举
 * <p>
 * Sql类型
 * Jdbc类型名
 * Java基础类型名
 * Java包装类名
 * Java包装类全名（包括包名）
 * import包？
 * 空值
 *
 * @author 宋欢
 */
public enum TypeEnum {
    /**
     * 整数
     */
    INTEGER(Types.INTEGER, "INTEGER", "int", Integer.class.getSimpleName(), Integer.class.getName(), false, "0"),

    /**
     * 长整数
     */
    BIGINT(Types.BIGINT, "BIGINT", "long", Long.class.getSimpleName(), Long.class.getName(), false, "0L"),

    /**
     * 浮点数
     */
    DECIMAL(Types.DECIMAL, "DECIMAL", "", BigDecimal.class.getSimpleName(), BigDecimal.class.getName(), true, "BigDecimal.ZERO"),

    /**
     * 定长字符串
     */
    CHAR(Types.CHAR, "CHAR", "", String.class.getSimpleName(), String.class.getName(), false, "''"),

    /**
     * 字符串
     */
    VARCHAR(Types.VARCHAR, "VARCHAR", "", String.class.getSimpleName(), String.class.getName(), false, "''"),

    /**
     * 长字符串
     */
    LONGVARCHAR(Types.LONGVARCHAR, "LONGVARCHAR", "", String.class.getSimpleName(), String.class.getName(), false, "''"),

    /**
     * 日期时间
     */
    TIMESTAMP(Types.TIMESTAMP, "TIMESTAMP", "", Date.class.getSimpleName(), Date.class.getName(), true, null),

    /**
     * 日期
     */
    DATE(Types.DATE, "DATE", "", Date.class.getSimpleName(), Date.class.getName(), true, null),

    /**
     * 时间
     */
    TIME(Types.TIME, "TIME", "", Date.class.getSimpleName(), Date.class.getName(), true, null),

    /**
     * 布尔
     */
    BOOLEAN(Types.BOOLEAN, "BOOLEAN", "boolean", Boolean.class.getSimpleName(), Boolean.class.getName(), false, "false"),

    /**
     * 空
     */
    NULL(Types.NULL, "NULL", "", Object.class.getSimpleName(), Object.class.getName(), false, null),

    ;

    /**
     * Sql类型
     */
    private final int sqlType;

    /**
     * Jdbc类型名
     */
    private final String jdbcName;

    /**
     * Java基础类型名
     */
    private final String javaBasic;

    /**
     * Java包装类名
     */
    private final String javaSimpleWrapper;

    /**
     * Java包装类全名（包括包名）
     */
    private final String javaWrapper;

    /**
     * 用Java包装类，需要引包？
     */
    private final boolean useImport;

    /**
     * null时默认值
     */
    private final String nullValue;

    TypeEnum(int sqlType, String jdbcName, String javaBasic, String javaSimpleWrapper, String javaWrapper, boolean useImport, String nullValue) {
        this.sqlType = sqlType;
        this.jdbcName = jdbcName;
        this.javaBasic = javaBasic;
        this.javaSimpleWrapper = javaSimpleWrapper;
        this.javaWrapper = javaWrapper;
        this.useImport = useImport;
        this.nullValue = nullValue;
    }

    public int getSqlType() {
        return sqlType;
    }

    public String getJdbcName() {
        return jdbcName;
    }

    public String getJavaBasic() {
        return javaBasic;
    }

    public String getJavaSimpleWrapper() {
        return javaSimpleWrapper;
    }

    public String getJavaWrapper() {
        return javaWrapper;
    }

    public boolean isUseImport() {
        return useImport;
    }

    public String getNullValue() {
        return nullValue;
    }

}
