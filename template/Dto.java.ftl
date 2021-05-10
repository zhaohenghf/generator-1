package ${packageName}.biz.dto;

import com.ichings.util.JsonParser;

import java.io.Serializable;
import java.util.Date;

/**
 * ${classTitle}
 *
 * @author ${authorName}
 */
public final class ${tableNameCamelUpperFirst}Dto implements Serializable {
    ${dtoFields}

    @Override
    public String toString() {
        return JsonParser.toJson(this);
    }

    ${dtoMethods}

}
