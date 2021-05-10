package ${packageName}.web.vo;

import com.ichings.common.page.VoCriteria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ${classTitle}
 * 查询条件
 *
 * @author ${authorName}
 */
@ApiModel
public final class ${tableNameCamelUpperFirst}CriteriaRequest extends VoCriteria {

    ${voReqCriteriaFields}

    ${voReqCriteriaMethods}

}
