package com.ichings.generator.tag;

import com.ichings.generator.db.ColumnSchema;
import com.ichings.generator.db.CommonColumn;
import com.ichings.generator.util.AssertUtils;
import com.ichings.generator.util.Check;
import com.ichings.generator.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Persistent Object 工具类
 *
 * @author 宋欢
 */
public final class PoUtils {
    /**
     * ${poMapperInsert}
     */
    public static String buildMapperInsert(List<ColumnSchema> list) {
        AssertUtils.nonNull(list, "list");

        List<String> result = new ArrayList<>();

        String leftTab = "\t";
        boolean firstLine = true;

        for (ColumnSchema c : list) {
            if (Check.isNull(c)) {
                continue;
            }

            String name = c.getName();
            String fieldName = c.getCamelLowerFirst();
            String defaultValue = c.getDefaultNullValue();
            if (Check.nonEmpty(defaultValue)) {
                defaultValue = defaultValue.replace("''", "");
            }

            AssertUtils.nonEmpty(name, "name");
            AssertUtils.nonEmpty(fieldName, "fieldName");

            if (SqlIgnore.isIgnore(SqlIgnore.TYPE_INSERT, name) ||
                    CommonColumn.isId(name) ||
                    c.isAllowNull()) {
                continue;
            }

            String code = firstLine ? "" : leftTab;
            code += String.format("@Mapping(source = \"%s\", target = \"%s\", defaultValue = \"%s\")",
                    fieldName, fieldName, defaultValue);
            result.add(code);
            firstLine = false;
        }

        return StringUtils.join(result, "\n");
    }

}
