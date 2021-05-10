package com.ichings.generator.controller;

import com.ichings.generator.db.DbSchema;
import com.ichings.generator.service.DbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 宋欢
 */
@Api(value = "DbController", tags = "数据库")
@RequestMapping("db")
@Validated
@RestController
public class DbController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbController.class);

    @Autowired
    private DbService service;

    @ApiOperation(value = "全部表名", produces = "application/json")
    @GetMapping(value = "/table-names")
    @ResponseBody
    public List<String> getTableNames() {
        LOGGER.info("getTableNames trace");

        return service.getTableNames();
    }

    @ApiOperation(value = "表的概要描述", produces = "application/json")
    @GetMapping(value = "/table-schema")
    @ResponseBody
    public DbSchema getTableSchema(String tableName) {
        LOGGER.info("getTableSchema trace, tableName: {}", tableName);

        List<String> list = new ArrayList<>();
        list.add(tableName);

        return service.getSchema(list);
    }

}
