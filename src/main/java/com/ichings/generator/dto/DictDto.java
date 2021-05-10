package com.ichings.generator.dto;

import com.ichings.generator.util.JsonParser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 数据字典配置
 *
 * @author 宋欢
 */
@ApiModel
public class DictDto implements Serializable {

    @ApiModelProperty(value = "目标文档目录（建议绝对路径）", required = false, dataType = "String", example = "target-dict")
    private String targetDir;

    @ApiModelProperty(value = "生成文档后，打开目录？", required = false, dataType = "Boolean", example = "true")
    private Boolean openDir;

    @ApiModelProperty(value = "支持的文档后缀，支持：html、doc、md", required = false, dataType = "String", example = "html")
    private String suffix;

    @ApiModelProperty(value = "描述", required = false, dataType = "String", example = "数据字典")
    private String description;

    @ApiModelProperty(value = "版本号", required = false, dataType = "String", example = "1.0")
    private String version;

    @ApiModelProperty(value = "表名列表，置空：全部表", required = false, dataType = "List", example = "")
    private List<String> tableNames;

    @Override
    public String toString() {
        return JsonParser.toJson(this);
    }

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

    public List<String> getTableNames() {
        return tableNames;
    }

    public void setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
    }

}
