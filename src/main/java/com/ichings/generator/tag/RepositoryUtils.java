package com.ichings.generator.tag;

import com.ichings.generator.db.ColumnSchema;
import com.ichings.generator.db.CommonColumn;
import com.ichings.generator.util.AssertUtils;
import com.ichings.generator.util.Check;
import com.ichings.generator.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository 工具类
 *
 * @author 宋欢
 */
public final class RepositoryUtils {
    /**
     * ${repositoryCheckAndInitializeInsert}
     */
    public static String buildCheckAndInitializeInsert(List<ColumnSchema> list) {
        AssertUtils.nonNull(list, "list");

        List<String> result = new ArrayList<>();

        String leftTab = "\t\t";
        boolean firstLine = true;

        for (ColumnSchema c : list) {
            if (Check.isNull(c)) {
                continue;
            }

            String name = c.getName();
            String fieldName = c.getCamelLowerFirst();
            String methodName = c.getCamelUpperFirst();

            AssertUtils.nonEmpty(name, "name");
            AssertUtils.nonEmpty(fieldName, "fieldName");
            AssertUtils.nonEmpty(methodName, "methodName");

            if (SqlIgnore.isIgnore(SqlIgnore.TYPE_INSERT, name) || c.isAllowNull()) {
                continue;
            }

            String code = firstLine ? "" : leftTab;
            if (CommonColumn.isId(name) || CommonColumn.isSort(name)) {
                code += String.format("AssertUtils.isPositive(r.get%s(), \"%s\");", methodName, fieldName);
            } else {
                code += String.format("AssertUtils.nonNull(r.get%s(), \"%s\");", methodName, fieldName);
            }

            result.add(code);
            firstLine = false;
        }

        return StringUtils.join(result, "\n");
    }

}
