package com.ichings.generator.db;

import com.ichings.generator.autoconfigure.DsnProperties;
import com.ichings.generator.util.AssertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Jdbc Connection Driver
 *
 * <pre>
 * <dependency>
 *     <groupId>mysql</groupId>
 *     <artifactId>mysql-connector-java</artifactId>
 * </dependency>
 * </pre>
 *
 * @author 宋欢
 */
public final class DbDriver {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbDriver.class);

    /**
     * 配置Key：用户名
     */
    private static final String PROP_USERNAME = "user";

    /**
     * 配置Key：密码
     */
    private static final String PROP_PASSWORD = "password";

    /**
     * 配置Key：读信息概要？
     */
    private static final String PROP_USE_INFORMATION_SCHEMA = "useInformationSchema";

    /**
     * 读信息概要？
     */
    private static final String USE_INFORMATION_SCHEMA = "true";

    /**
     * 域名或Ip、数据库名、参数
     * jdbc:mysql://127.0.0.1:3306/database?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&useAffectedRows=true&allowMultiQueries=true
     */
    private final String url;

    /**
     * 用户名
     */
    private final String username;

    /**
     * 密码
     */
    private final String password;

    public DbDriver(DsnProperties properties) {
        AssertUtils.nonNull(properties, "properties");

        String url = properties.getUrl();
        String username = properties.getUsername();
        String password = properties.getPassword();

        AssertUtils.nonEmpty(url, "url");
        AssertUtils.nonEmpty(username, "username");

        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * Open Connection
     */
    public Connection getConnection() {
        try {
            Properties props = new Properties();
            props.setProperty(PROP_USERNAME, username);
            props.setProperty(PROP_PASSWORD, password);
            props.setProperty(PROP_USE_INFORMATION_SCHEMA, USE_INFORMATION_SCHEMA);

            return DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            LOGGER.error("getConnection failed, url: {}, username: {}, throwable: ", url, username, e);
            return null;
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
