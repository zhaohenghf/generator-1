package ${packageName}.infrastructure.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * ${classTitle}
 *
 * @author ${authorName}
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ${tableNameCamelUpperFirst}Mapper {
    /**
     * ${tableNameCamelUpperFirst} -> ${tableNameCamelUpperFirst}
     *
     * @param record ${tableNameCamelUpperFirst}
     * @return ${tableNameCamelUpperFirst}
     */
    ${poMapperInsert}
    ${tableNameCamelUpperFirst} insert(${tableNameCamelUpperFirst} record);

}
