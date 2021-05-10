package com.ichings.generator.service;

import com.ichings.generator.db.DbSchema;

import java.util.List;

/**
 * Jdbc
 *
 * @author 宋欢
 */
public interface DbService {
    /**
     * 获取全部表名
     *
     * @return list
     */
    List<String> getTableNames();

    /**
     * 获取库的概要描述
     *
     * @param tableNames 表名
     * @return DbSchema
     */
    DbSchema getSchema(List<String> tableNames);

}
