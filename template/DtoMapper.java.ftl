package ${packageName}.biz.dto;

import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst};
import ${packageName}.infrastructure.model.${tableNameCamelUpperFirst}Attribute;
import com.ichings.util.BooleanCast;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * ${classTitle}
 *
 * @author ${authorName}
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = BooleanCast.class)
public interface ${tableNameCamelUpperFirst}DtoMapper {
    /**
     * ${tableNameCamelUpperFirst}Dto -> ${tableNameCamelUpperFirst}
     *
     * @param dto ${tableNameCamelUpperFirst}Dto
     * @return ${tableNameCamelUpperFirst}
     */
    ${tableNameCamelUpperFirst} dtoToPo(${tableNameCamelUpperFirst}Dto dto);

    /**
     * ${tableNameCamelUpperFirst}Dto -> ${tableNameCamelUpperFirst}
     *
     * @param list ${tableNameCamelUpperFirst}Dto
     * @return ${tableNameCamelUpperFirst}
     */
    List<${tableNameCamelUpperFirst}> dtoToPo(List<${tableNameCamelUpperFirst}Dto> list);

    /**
     * ${tableNameCamelUpperFirst}Dto -> ${tableNameCamelUpperFirst}Attribute
     *
     * @param dto ${tableNameCamelUpperFirst}Dto
     * @return ${tableNameCamelUpperFirst}Attribute
     */
    ${tableNameCamelUpperFirst}Attribute dtoToAttribute(${tableNameCamelUpperFirst}Dto dto);

    /**
     * ${tableNameCamelUpperFirst} & ${tableNameCamelUpperFirst}Attribute -> ${tableNameCamelUpperFirst}Dto
     *
     * @param record ${tableNameCamelUpperFirst}
     * @param attribute ${tableNameCamelUpperFirst}Attribute
     * @return ${tableNameCamelUpperFirst}Dto
     */
    ${tableNameCamelUpperFirst}Dto poToDto(${tableNameCamelUpperFirst} record, ${tableNameCamelUpperFirst}Attribute attribute);

    /**
     * ${tableNameCamelUpperFirst} -> ${tableNameCamelUpperFirst}Dto
     *
     * @param list ${tableNameCamelUpperFirst}
     * @return ${tableNameCamelUpperFirst}Dto
     */
    List<${tableNameCamelUpperFirst}Dto> poToDto(List<${tableNameCamelUpperFirst}> list);

}
