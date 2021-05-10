package com.ichings.generator.main;

import com.ichings.generator.autoconfigure.CodeProperties;
import com.ichings.generator.autoconfigure.DictProperties;
import com.ichings.generator.autoconfigure.DocketProperties;
import com.ichings.generator.autoconfigure.DsnProperties;
import com.ichings.generator.db.DbDriver;
import com.ichings.generator.util.Snowflake;
import com.ichings.generator.util.Swagger3;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Connection;

/**
 * @author 宋欢
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableSwagger2
@EnableScheduling
@ComponentScan(basePackages = {"com.ichings.generator"})
@EnableConfigurationProperties({
        DsnProperties.class, DocketProperties.class,
        CodeProperties.class, DictProperties.class})
public class Application {
    /**
     * 机器id
     */
    private static final long WORKER_ID = 0;

    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Snowflake snowflake() {
        return new Snowflake(WORKER_ID);
    }

    @Bean
    public Connection dbConn(DbDriver dbDriver) {
        return dbDriver.getConnection();
    }

    @Bean
    public DbDriver dbDriver(DsnProperties properties) {
        return new DbDriver(properties);
    }

    @Bean
    public Docket swaggerSpringMvcPlugin(DocketProperties properties) {
        return new Swagger3(properties).getDocket();
    }

}
