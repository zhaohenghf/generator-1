package ${packageName}.web.vo;

import com.ichings.util.JsonParser;

import java.io.Serializable;
import java.util.Date;

/**
 * ${classTitle}
 *
 * @author ${authorName}
 */
public final class ${tableNameCamelUpperFirst}Vo implements Serializable {

    ${voFields}

    @Override
    public String toString() {
        return JsonParser.toJson(this);
    }

    ${voMethods}

}
