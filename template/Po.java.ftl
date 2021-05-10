package ${packageName}.infrastructure.model;

import com.ichings.util.JsonParser;

import java.io.Serializable;
import java.util.Date;

/**
 * ${classTitle}
 *
 * @author ${authorName}
 */
public final class ${tableNameCamelUpperFirst} implements Serializable {
    ${poFields}

    @Override
    public String toString() {
        return JsonParser.toJson(this);
    }

    ${poMethods}

}
