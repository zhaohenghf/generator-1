package com.ichings.generator.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * 模板引擎
 *
 * <pre>
 * <dependency>
 *     <groupId>org.freemarker</groupId>
 *     <artifactId>freemarker</artifactId>
 * </dependency>
 * </pre>
 *
 * @author 宋欢
 */
public final class FreeMarkerUtils {
    /**
     * 模板文件后缀
     */
    private static final String SUFFIX_TEMPLATE = ".ftl";

    /**
     * 默认的编码
     */
    private static final String ENCODING = StandardCharsets.UTF_8.name();

    /**
     * 配置版本
     */
    private static final Version VERSION = Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;

    /**
     * 生成代码
     *
     * @param templateDir 模板目录
     * @param targetDir   目标代码目录
     * @param data        数据，name => code
     * @throws IOException       写文件失败
     * @throws TemplateException 模板错误
     */
    public static void process(String templateDir, String targetDir, Map<String, String> data) throws IOException, TemplateException {
        String[] templateNames = getTemplateNames(templateDir);
        Assert.checkArgument(Objects.nonNull(templateNames) && templateNames.length > 0, "templateNames can't be empty");

        Configuration configuration = newConfiguration(templateDir);
        for (String name : templateNames) {
            write(configuration, name, targetDir, data);
        }
    }

    /**
     * 写单个文件
     *
     * @param configuration Configuration
     * @param templateName  模板名
     * @param targetDir     目标代码目录
     * @param data          数据，name => code
     * @throws IOException       写文件失败
     * @throws TemplateException 模板错误
     */
    public static void write(Configuration configuration, String templateName, String targetDir, Map<String, String> data) throws IOException, TemplateException {
        AssertUtils.nonNull(configuration, "configuration");
        AssertUtils.nonEmpty(templateName, "templateName");
        AssertUtils.nonEmpty(targetDir, "targetDir");

        Template template = configuration.getTemplate(templateName);
        String targetName = getTargetName(templateName);

        String targetPath = targetDir + FileUtils.SEPARATOR + targetName;
        File targetFile = new File(targetPath);

        Writer out = new FileWriter(targetFile);
        template.process(data, out);
        out.close();
    }

    /**
     * 模板名 -> 目标文件名
     *
     * @param templateName 模板名
     * @return 目标文件名
     */
    public static String getTargetName(String templateName) {
        AssertUtils.nonEmpty(templateName, "templateName");
        Assert.checkArgument(templateName.endsWith(SUFFIX_TEMPLATE),
                String.format("templateName '%s' must be end with '%s'", templateName, SUFFIX_TEMPLATE));

        return templateName.substring(0, templateName.lastIndexOf(SUFFIX_TEMPLATE));
    }

    /**
     * 获取配置
     *
     * @param templateDir 模板目录
     * @return Configuration
     * @throws IOException 读模板失败
     */
    public static Configuration newConfiguration(String templateDir) throws IOException {
        AssertUtils.nonEmpty(templateDir, "templateDir");

        File f = new File(templateDir);
        Assert.checkArgument(f.isDirectory(), String.format("templateDir '%s' must be a directory", f.getAbsolutePath()));

        Configuration configuration = new Configuration(VERSION);
        configuration.setDirectoryForTemplateLoading(f);
        configuration.setDefaultEncoding(ENCODING);

        return configuration;
    }

    /**
     * 获取全部模板名
     *
     * @param templateDir 模板目录
     * @return 模板名列表
     */
    public static String[] getTemplateNames(String templateDir) {
        AssertUtils.nonEmpty(templateDir, "templateDir");

        File f = new File(templateDir);
        Assert.checkArgument(f.isDirectory(), String.format("templateDir '%s' must be a directory", f.getAbsolutePath()));

        return f.list();
    }

}
