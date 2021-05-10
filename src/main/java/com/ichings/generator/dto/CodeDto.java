package com.ichings.generator.dto;

import com.ichings.generator.util.JsonParser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 生成代码配置
 *
 * @author 宋欢
 */
@ApiModel
public class CodeDto implements Serializable {

    @ApiModelProperty(value = "根包名", required = false, dataType = "String", example = "com.domain.sample")
    private String packageName;

    @ApiModelProperty(value = "作者名", required = false, dataType = "String", example = "张三")
    private String authorName;

    @ApiModelProperty(value = "模板目录（建议绝对路径）", required = false, dataType = "String", example = "template")
    private String templateDir;

    @ApiModelProperty(value = "目标代码目录（建议绝对路径）", required = false, dataType = "String", example = "target-code")
    private String targetDir;

    @ApiModelProperty(value = "表名列表，置空：全部表", required = false, dataType = "List", example = "[]")
    private List<String> tableNames;

    @Override
    public String toString() {
        return JsonParser.toJson(this);
    }

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

    public List<String> getTableNames() {
        return tableNames;
    }

    public void setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
    }

}
