package com.ichings.generator.tag;

import com.ichings.generator.db.ColumnSchema;
import com.ichings.generator.util.AssertUtils;
import com.ichings.generator.util.Check;
import com.ichings.generator.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * import ***;
 *
 * @author 宋欢
 */
public final class PojoImport {
    /**
     * import ***;
     * import ***;
     * ...
     */
    public static String getCode(PojoEnum type, List<ColumnSchema> list) {
        AssertUtils.nonNull(type, "type");
        AssertUtils.nonNull(list, "list");

        Set<String> javaWrapperSet = new TreeSet<>(Comparator.naturalOrder());
        for (ColumnSchema c : list) {
            if (Check.isNull(c)) {
                continue;
            }

            if (PojoIgnore.isIgnore(type, c.getName())) {
                continue;
            }

            if (c.isUseImport()) {
                String javaWrapper = c.getJavaWrapper();
                javaWrapperSet.add(javaWrapper);
            }
        }

        if (Check.isEmpty(javaWrapperSet)) {
            return "";
        }

        List<String> result = new ArrayList<>();
        for (String n : javaWrapperSet) {
            String code = "import " + n + ";";
            result.add(code);
        }

        return StringUtils.join(result, "\n") + "\n";
    }

}
