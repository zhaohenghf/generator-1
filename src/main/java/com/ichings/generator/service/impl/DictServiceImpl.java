package com.ichings.generator.service.impl;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.ichings.generator.autoconfigure.DsnProperties;
import com.ichings.generator.dto.DictDto;
import com.ichings.generator.service.DbService;
import com.ichings.generator.service.DictService;
import com.ichings.generator.util.AssertUtils;
import com.ichings.generator.util.Check;
import com.ichings.generator.util.FileUtils;
import com.ichings.generator.util.ScrewUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 宋欢
 */
@Service
public class DictServiceImpl implements DictService, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictServiceImpl.class);

    /**
     * HikariCP数据源
     */
    private HikariDataSource dataSource;

    @Autowired
    private DsnProperties dsnProperties;

    @Autowired
    private DbService dbService;

    @Override
    public void execute(DictDto dto) {
        AssertUtils.nonNull(dto, "dto");

        String targetDir = dto.getTargetDir();
        Boolean openDir = dto.getOpenDir();
        String suffix = dto.getSuffix();
        String description = dto.getDescription();
        String version = dto.getVersion();

        List<String> tableNames = dto.getTableNames();
        if (Check.isEmpty(tableNames)) {
            tableNames = dbService.getTableNames();
        }

        AssertUtils.nonEmpty(targetDir, "targetDir");
        AssertUtils.nonNull(openDir, "openDir");
        AssertUtils.nonEmpty(suffix, "suffix");
        AssertUtils.nonEmpty(description, "description");
        AssertUtils.nonEmpty(version, "version");
        AssertUtils.nonEmpty(tableNames, "tableNames");

        if (!FileUtils.rmdir(targetDir)) {
            LOGGER.warn("execute warn, remove directory failed, targetDir: {}", targetDir);
        }

        FileUtils.mkdirs(targetDir);

        EngineFileType fileType = ScrewUtils.suffixToFileType(suffix);
        EngineConfig engineConfig = ScrewUtils.getEngineConfig(targetDir, openDir, fileType);
        ProcessConfig produceConfig = ScrewUtils.getProcessConfig(tableNames);

        Configuration configuration = Configuration.builder().
                version(version).
                description(description).
                engineConfig(engineConfig).
                produceConfig(produceConfig).
                dataSource(dataSource).
                build();

        DocumentationExecute executor = new DocumentationExecute(configuration);
        executor.execute();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String url = dsnProperties.getUrl();
        String username = dsnProperties.getUsername();
        String password = dsnProperties.getPassword();

        HikariConfig hikariConfig = ScrewUtils.newHikariConfig(url, username, password);
        dataSource = new HikariDataSource(hikariConfig);
    }

}
