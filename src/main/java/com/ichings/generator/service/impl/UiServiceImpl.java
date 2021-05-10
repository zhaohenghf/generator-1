package com.ichings.generator.service.impl;

import com.ichings.generator.autoconfigure.CodeProperties;
import com.ichings.generator.autoconfigure.DictProperties;
import com.ichings.generator.db.DbUtils;
import com.ichings.generator.service.UiService;
import com.ichings.generator.util.AssertUtils;
import com.ichings.generator.util.Check;
import com.ichings.generator.util.ScrewUtils;
import com.ichings.generator.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 宋欢
 */
@Service
public class UiServiceImpl implements UiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UiServiceImpl.class);

    /**
     * 根包名
     */
    private static final String PACKAGE_NAME = "packageName";

    /**
     * 作者名
     */
    private static final String AUTHOR_NAME = "authorName";

    /**
     * 模板目录
     */
    private static final String TEMPLATE_DIR = "templateDir";

    /**
     * 目标代码目录、目标文档目录
     */
    private static final String TARGET_DIR = "targetDir";

    /**
     * 生成文档后，打开目录？
     */
    private static final String OPEN_DIR = "openDir";

    /**
     * 支持的文档后缀，支持：html、doc、md
     */
    private static final String SUFFIX = "suffix";

    /**
     * 描述
     */
    private static final String DESCRIPTION = "description";

    /**
     * 版本号
     */
    private static final String VERSION = "version";

    /**
     * 域名或Ip、数据库名、参数
     */
    private static final String DB_URL = "dbUrl";

    /**
     * 表名列表
     */
    private static final String TABLE_NAMES = "tableNames";

    @Autowired
    private CodeProperties codeProperties;

    @Autowired
    private DictProperties dictProperties;

    @Autowired
    private Connection dbConn;

    @Override
    public void renderCode(Model m) {
        AssertUtils.nonNull(m, "m");

        String packageName = codeProperties.getPackageName();
        String authorName = codeProperties.getAuthorName();
        String templateDir = codeProperties.getTemplateDir();
        String targetDir = codeProperties.getTargetDir();

        m.addAttribute(PACKAGE_NAME, Check.nonNull(packageName) ? packageName : "");
        m.addAttribute(AUTHOR_NAME, Check.nonNull(authorName) ? authorName : "");
        m.addAttribute(TEMPLATE_DIR, Check.nonNull(templateDir) ? templateDir : "");
        m.addAttribute(TARGET_DIR, Check.nonNull(targetDir) ? targetDir : "");
    }

    @Override
    public void renderDict(Model m) {
        AssertUtils.nonNull(m, "m");

        String targetDir = dictProperties.getTargetDir();
        Boolean openDir = dictProperties.getOpenDir();
        String suffix = dictProperties.getSuffix();
        String description = dictProperties.getDescription();
        String version = dictProperties.getVersion();

        m.addAttribute(TARGET_DIR, Check.nonNull(targetDir) ? targetDir : "");
        m.addAttribute(OPEN_DIR, Check.nonNull(openDir) ? openDir : ScrewUtils.OPEN_DIR);
        m.addAttribute(SUFFIX, Check.nonNull(suffix) ? suffix : ScrewUtils.FILE_TYPE.getFileSuffix());
        m.addAttribute(DESCRIPTION, Check.nonNull(description) ? StringUtils.encoding(description, null) : "");
        m.addAttribute(VERSION, Check.nonNull(version) ? version : "");
    }

    @Override
    public void renderDb(Model m) {
        AssertUtils.nonNull(m, "m");

        String dbUrl = null;
        List<String> tableNames = null;
        try {
            dbUrl = DbUtils.getSchema(dbConn, null).getUrl();
            tableNames = DbUtils.getTableNames(dbConn);
        } catch (SQLException e) {
            LOGGER.error("renderDb failed, throwable: ", e);
        }

        m.addAttribute(DB_URL, Check.nonNull(dbUrl) ? dbUrl : "");
        m.addAttribute(TABLE_NAMES, Check.nonNull(tableNames) ? tableNames : new ArrayList<>());
    }

}
