package com.ichings.generator.db;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * 类型工具集
 *
 * @author 宋欢
 */
public final class TypeUtils {
    /**
     * Sql类型 => 枚举
     */
    private static final Map<Integer, TypeEnum> TYPE_ENUM_MAP = new HashMap<>();

    static {
        // Sql类型 => 枚举
        TYPE_ENUM_MAP.put(Types.INTEGER, TypeEnum.INTEGER);
        TYPE_ENUM_MAP.put(Types.BIGINT, TypeEnum.BIGINT);
        TYPE_ENUM_MAP.put(Types.DECIMAL, TypeEnum.DECIMAL);
        TYPE_ENUM_MAP.put(Types.CHAR, TypeEnum.CHAR);
        TYPE_ENUM_MAP.put(Types.VARCHAR, TypeEnum.VARCHAR);
        TYPE_ENUM_MAP.put(Types.LONGVARCHAR, TypeEnum.LONGVARCHAR);
        TYPE_ENUM_MAP.put(Types.TIMESTAMP, TypeEnum.TIMESTAMP);
        TYPE_ENUM_MAP.put(Types.DATE, TypeEnum.DATE);
        TYPE_ENUM_MAP.put(Types.TIME, TypeEnum.TIME);
        TYPE_ENUM_MAP.put(Types.BOOLEAN, TypeEnum.BOOLEAN);
        TYPE_ENUM_MAP.put(Types.NULL, TypeEnum.NULL);
    }

    private TypeUtils() {
    }

    /**
     * 通过 Sql类型 获取 枚举
     */
    public static TypeEnum getEnum(int type) {
        return TYPE_ENUM_MAP.get(type);
    }

}
