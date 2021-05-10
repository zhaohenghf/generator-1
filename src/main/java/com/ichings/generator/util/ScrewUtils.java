package com.ichings.generator.util;

import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 生成数据字典
 * 支持：HTML、WORD、Markdown
 *
 * <pre>
 * <dependency>
 *     <groupId>cn.smallbun.screw</groupId>
 *     <artifactId>screw-core</artifactId>
 * </dependency>
 *
 * <dependency>
 *     <groupId>org.freemarker</groupId>
 *     <artifactId>freemarker</artifactId>
 * </dependency>
 *
 * <dependency>
 *     <groupId>com.zaxxer</groupId>
 *     <artifactId>HikariCP</artifactId>
 * </dependency>
 * </pre>
 *
 * @author 宋欢
 */
public final class ScrewUtils {
    /**
     * 文档模板类型
     */
    private static final EngineTemplateType PRODUCE_TYPE = EngineTemplateType.freemarker;

    /**
     * 生成文档后，默认打开目录？
     */
    public static final Boolean OPEN_DIR = true;

    /**
     * 默认的文档类型，支持：EngineFileType.HTML、EngineFileType.WORD、EngineFileType.MD
     */
    public static final EngineFileType FILE_TYPE = EngineFileType.HTML;

    /**
     * 配置Key：读信息概要？
     */
    private static final String PROP_USE_INFORMATION_SCHEMA = "useInformationSchema";

    /**
     * 读信息概要？
     */
    private static final String USE_INFORMATION_SCHEMA = String.valueOf(true);

    /**
     * 文档名后缀分隔符
     */
    private static final String SUFFIX_SEPARATOR = ".";

    /**
     * 支持的文档类型
     * .html => EngineFileType.HTML
     * .doc => EngineFileType.WORD
     * .md => EngineFileType.MD
     */
    private static final Map<String, EngineFileType> SUPPORTED_FILE_TYPE_MAP = new HashMap<>();

    static {
        SUPPORTED_FILE_TYPE_MAP.put(EngineFileType.HTML.getFileSuffix(), EngineFileType.HTML);
        SUPPORTED_FILE_TYPE_MAP.put(EngineFileType.WORD.getFileSuffix(), EngineFileType.WORD);
        SUPPORTED_FILE_TYPE_MAP.put(EngineFileType.MD.getFileSuffix(), EngineFileType.MD);
    }

    /**
     * 表配置
     *
     * @param tableNames 表名列表
     * @return ProcessConfig
     */
    public static ProcessConfig getProcessConfig(List<String> tableNames) {
        AssertUtils.nonEmpty(tableNames, "tableNames");

        Set<String> tableSet = new HashSet<>();
        for (String name : tableNames) {
            name = StringUtils.trimSpace(name);
            if (Check.nonEmpty(name)) {
                tableSet.add(name);
            }
        }

        AssertUtils.nonEmpty(tableSet, "tableSet");

        return ProcessConfig.builder().
                designatedTableName(new ArrayList<>(tableSet)).
                designatedTablePrefix(new ArrayList<>()).
                designatedTableSuffix(new ArrayList<>()).
                build();
    }

    /**
     * 文件、目录和文档类型配置
     *
     * @param targetDir 目标文档目录（建议绝对路径）
     * @param openDir   生成文档后，打开目录？默认：打开
     * @param fileType  文档类型，默认：EngineFileType.HTML，支持：EngineFileType.HTML、EngineFileType.WORD、EngineFileType.MD
     * @return EngineConfig
     */
    public static EngineConfig getEngineConfig(String targetDir, Boolean openDir, EngineFileType fileType) {
        AssertUtils.nonEmpty(targetDir, "targetDir");

        return EngineConfig.builder().
                fileOutputDir(targetDir).
                openOutputDir(Objects.nonNull(openDir) ? openDir : OPEN_DIR).
                fileType(Objects.nonNull(fileType) ? fileType : FILE_TYPE).
                produceType(PRODUCE_TYPE).
                build();
    }

    /**
     * HikariCP配置
     *
     * @param url      域名或Ip
     * @param username 用户名
     * @param password 密码
     * @return HikariConfig
     */
    public static HikariConfig newHikariConfig(String url, String username, String password) {
        AssertUtils.nonEmpty(url, "url");
        AssertUtils.nonEmpty(username, "username");
        AssertUtils.nonEmpty(password, "password");

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.addDataSourceProperty(PROP_USE_INFORMATION_SCHEMA, USE_INFORMATION_SCHEMA);
        return hikariConfig;
    }

    /**
     * Suffix -> EngineFileType
     *
     * @param suffix 文档名后缀
     * @return EngineFileType
     */
    public static EngineFileType suffixToFileType(String suffix) {
        AssertUtils.nonEmpty(suffix, "suffix");

        suffix = suffix.trim().toLowerCase();

        String key = suffix.startsWith(SUFFIX_SEPARATOR) ? suffix : SUFFIX_SEPARATOR + suffix;
        return SUPPORTED_FILE_TYPE_MAP.get(key);
    }

}
