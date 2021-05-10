package ${packageName}.web.vo;

import com.ichings.util.JsonParser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ${classTitle}
 * 新增一条记录
 *
 * @author ${authorName}
 */
@ApiModel
public final class ${tableNameCamelUpperFirst}CreateRequest implements Serializable {

    ${voReqCreateFields}

    @Override
    public String toString() {
        return JsonParser.toJson(this);
    }

    ${voReqCreateMethods}

}
