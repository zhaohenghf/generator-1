package ${packageName}.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * ${classTitle}
 * 修改一条记录
 *
 * @author ${authorName}
 */
@ApiModel
public final class ${tableNameCamelUpperFirst}UpdateRequest implements Serializable {

    ${voReqUpdateFields}

    @Override
    public String toString() {
        return JsonParser.toJson(this);
    }

    ${voReqUpdateMethods}

}
