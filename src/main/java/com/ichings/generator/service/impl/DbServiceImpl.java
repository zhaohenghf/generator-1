package com.ichings.generator.service.impl;

import com.ichings.generator.db.DbSchema;
import com.ichings.generator.db.DbUtils;
import com.ichings.generator.service.DbService;
import com.ichings.generator.util.BizException;
import com.ichings.generator.util.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 宋欢
 */
@Service
public class DbServiceImpl implements DbService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbServiceImpl.class);

    @Autowired
    private Connection dbConn;

    @Override
    public List<String> getTableNames() {
        try {
            return DbUtils.getTableNames(dbConn);
        } catch (SQLException e) {
            LOGGER.error("getTableNames failed, throwable: ", e);
            throw new BizException(Errors.DB_ERR);
        }
    }

    @Override
    public DbSchema getSchema(List<String> tableNames) {
        try {
            return DbUtils.getSchema(dbConn, tableNames);
        } catch (SQLException e) {
            LOGGER.error("getSchema failed, throwable: ", e);
            throw new BizException(Errors.DB_ERR);
        }
    }

}
