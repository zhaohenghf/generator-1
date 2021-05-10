package com.ichings.generator.service.impl;

import com.ichings.generator.db.DbSchema;
import com.ichings.generator.db.TableSchema;
import com.ichings.generator.dto.CodeDto;
import com.ichings.generator.service.CodeService;
import com.ichings.generator.service.DbService;
import com.ichings.generator.service.TagService;
import com.ichings.generator.util.Assert;
import com.ichings.generator.util.AssertUtils;
import com.ichings.generator.util.BizException;
import com.ichings.generator.util.Check;
import com.ichings.generator.util.Errors;
import com.ichings.generator.util.FileUtils;
import com.ichings.generator.util.FreeMarkerUtils;
import com.ichings.generator.util.Snowflake;
import com.ichings.generator.util.TargetCode;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 宋欢
 */
@Service
public class CodeServiceImpl implements CodeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeServiceImpl.class);

    @Autowired
    private DbService dbService;

    @Autowired
    private TagService tagService;

    @Autowired
    private Snowflake snowflake;

    @Override
    public void execute(CodeDto dto) {
        AssertUtils.nonNull(dto, "dto");

        String templateDir = dto.getTemplateDir();
        String targetDir = dto.getTargetDir();
        List<String> tableNames = dto.getTableNames();
        if (Check.isEmpty(tableNames)) {
            tableNames = dbService.getTableNames();
        }

        AssertUtils.nonEmpty(templateDir, "templateDir");
        AssertUtils.nonEmpty(targetDir, "targetDir");
        AssertUtils.nonEmpty(tableNames, "tableNames");

        Assert.checkState(FileUtils.rmdir(targetDir), "rmdir failed, targetDir: " + targetDir);
        Assert.checkState(FileUtils.mkdirs(targetDir), "mkdir failed, targetDir: " + targetDir);

        DbSchema dbSchema = dbService.getSchema(tableNames);
        AssertUtils.nonNull(dbSchema, "dbSchema");

        List<TableSchema> tableList = dbSchema.getTableList();
        AssertUtils.nonEmpty(tableList, "tableList");

        for (TableSchema schema : tableList) {
            AssertUtils.nonNull(schema, "tableSchema");

            Map<String, String> data = tagService.getData(dto, schema);
            process(templateDir, targetDir, data);
        }
    }

    @Override
    public void process(String templateDir, String targetDir, Map<String, String> data) {
        AssertUtils.nonEmpty(templateDir, "templateDir");
        AssertUtils.nonEmpty(targetDir, "targetDir");
        AssertUtils.nonEmpty(data, "data");

        String tmpDir = targetDir + FileUtils.SEPARATOR + snowflake.nextId();
        Assert.checkState(FileUtils.mkdirs(tmpDir), "mkdir failed, tmpDir: " + tmpDir);

        try {
            FreeMarkerUtils.process(templateDir, tmpDir, data);
            tmpToTarget(tmpDir, targetDir);
        } catch (IOException | TemplateException e) {
            LOGGER.error("process failed, templateDir: {}, tmpDir: {}, data: {}, throwable: ", templateDir, tmpDir, data, e);
            throw new BizException(Errors.SYSTEM_RUN_ERR);
        }

        Assert.checkState(FileUtils.rmdir(tmpDir), "rmdir failed, tmpDir: " + tmpDir);
    }

    @Override
    public void tmpToTarget(String tmpDir, String targetDir) throws IOException {
        AssertUtils.nonEmpty(tmpDir, "tmpDir");
        AssertUtils.nonEmpty(targetDir, "targetDir");

        File[] list = new File(tmpDir).listFiles();
        if (Check.isNull(list)) {
            return;
        }

        for (File f : list) {
            String fileName = TargetCode.getName(f);
            if (Check.isEmpty(fileName)) {
                fileName = f.getName();
            }

            String targetPath = targetDir + FileUtils.SEPARATOR + fileName;
            FileCopyUtils.copy(f, new File(targetPath));
        }
    }

}
