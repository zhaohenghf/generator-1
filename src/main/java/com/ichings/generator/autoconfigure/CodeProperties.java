package com.ichings.generator.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 生成代码配置
 *
 * @author 宋欢
 */
@ConfigurationProperties(prefix = "code")
public class CodeProperties {
    /**
     * 根包名
     * 如：com.example.demo
     */
    private String packageName;

    /**
     * 生成代码的作者名
     * 如：张三
     */
    private String authorName;

    /**
     * 模板目录
     */
    private String templateDir;

    /**
     * 目标代码目录
     */
    private String targetDir;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTemplateDir() {
        return templateDir;
    }

    public void setTemplateDir(String templateDir) {
        this.templateDir = templateDir;
    }

    public String getTargetDir() {
        return targetDir;
    }

    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }

}
