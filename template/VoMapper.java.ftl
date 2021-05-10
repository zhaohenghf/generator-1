package ${packageName}.web.vo;

import ${packageName}.biz.dto.${tableNameCamelUpperFirst}Dto;
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst}Criteria;
import com.ichings.util.BooleanCast;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * ${classTitle}
 *
 * @author ${authorName}
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = BooleanCast.class)
public interface ${tableNameCamelUpperFirst}VoMapper {
    /**
     * ${tableNameCamelUpperFirst}CriteriaRequest -> ${tableNameCamelUpperFirst}Criteria
     *
     * @param request ${tableNameCamelUpperFirst}CriteriaRequest
     * @return ${tableNameCamelUpperFirst}Criteria
     */
    ${tableNameCamelUpperFirst}Criteria voToDto(${tableNameCamelUpperFirst}CriteriaRequest request);

    /**
     * ${tableNameCamelUpperFirst}Dto -> ${tableNameCamelUpperFirst}Vo
     *
     * @param record ${tableNameCamelUpperFirst}Dto
     * @return ${tableNameCamelUpperFirst}Vo
     */
    ${tableNameCamelUpperFirst}Vo dtoToVo(${tableNameCamelUpperFirst}Dto record);

    /**
     * ${tableNameCamelUpperFirst}Dto -> ${tableNameCamelUpperFirst}Vo
     *
     * @param list ${tableNameCamelUpperFirst}Dto
     * @return ${tableNameCamelUpperFirst}Vo
     */
    List<${tableNameCamelUpperFirst}Vo> dtoToVo(List<${tableNameCamelUpperFirst}Dto> list);

    /**
     * ${tableNameCamelUpperFirst}CreateRequest -> ${tableNameCamelUpperFirst}Dto
     *
     * @param request ${tableNameCamelUpperFirst}CreateRequest
     * @return ${tableNameCamelUpperFirst}Dto
     */
    ${tableNameCamelUpperFirst}Dto voToDto(${tableNameCamelUpperFirst}CreateRequest request);

    /**
     * ${tableNameCamelUpperFirst}UpdateRequest -> ${tableNameCamelUpperFirst}Dto
     *
     * @param request ${tableNameCamelUpperFirst}UpdateRequest
     * @return ${tableNameCamelUpperFirst}Dto
     */
    ${tableNameCamelUpperFirst}Dto voToDto(${tableNameCamelUpperFirst}UpdateRequest request);

}
