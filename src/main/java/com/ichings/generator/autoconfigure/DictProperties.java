package com.ichings.generator.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 数据字典配置
 *
 * @author 宋欢
 */
@ConfigurationProperties(prefix = "dict")
public class DictProperties {
    /**
     * 目标文档目录
     */
    private String targetDir;

    /**
     * 生成文档后，默认打开目录？
     */
    private Boolean openDir;

    /**
     * 支持的文档后缀，支持：html、doc、md
     */
    private String suffix;

    /**
     * 描述
     */
    private String description;

    /**
     * 版本号
     */
    private String version;

    public String getTargetDir() {
        return targetDir;
    }

    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }

    public Boolean getOpenDir() {
        return openDir;
    }

    public void setOpenDir(Boolean openDir) {
        this.openDir = openDir;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
