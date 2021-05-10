package com.ichings.generator.tag;

import com.ichings.generator.db.ColumnSchema;
import com.ichings.generator.util.AssertUtils;
import com.ichings.generator.util.Check;
import com.ichings.generator.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Persistent Object
 * Data Transfer Object
 * View Object
 * 工具类
 *
 * @author 宋欢
 */
public final class PojoUtils {
    /**
     * Boolean 标记
     */
    private static final String BOOL_MARK1 = "？";
    private static final String BOOL_MARK2 = "1-是";
    private static final String BOOL_MARK3 = "0-否";

    public static final String NUM_TRUE = "1";
    public static final String NUM_FALSE = "0";
    public static final String BOOL_TRUE = "true";
    public static final String BOOL_FALSE = "false";

    /**
     * private Type ***;
     * private Type ***;
     * ...
     */
    public static String buildFields(PojoEnum type, List<ColumnSchema> list) {
        AssertUtils.nonNull(type, "type");
        AssertUtils.nonNull(list, "list");

        List<String> result = new ArrayList<>();

        String leftTab = "\t";
        boolean firstLine = true;

        for (ColumnSchema c : list) {
            if (Check.isNull(c)) {
                continue;
            }

            if (PojoIgnore.isIgnore(type, c.getName())) {
                continue;
            }

            String value = c.getRemark();
            String dataType = c.getJavaSimpleWrapper();
            String fieldName = c.getCamelLowerFirst();
            String example = c.getDefaultValue();
            boolean required = c.isUniqueKey();
            boolean isBool = guessBool(type, c);
            if (isBool) {
                dataType = Boolean.class.getSimpleName();
                example = NUM_TRUE.equals(example) ? BOOL_TRUE : BOOL_FALSE;
            }

            AssertUtils.nonEmpty(value, "value");
            AssertUtils.nonEmpty(dataType, "dataType");
            AssertUtils.nonEmpty(fieldName, "fieldName");

            String code = firstLine ? "" : leftTab;
            if (PojoEnum.isVo(type) ||
                    PojoEnum.isVoCreate(type) ||
                    PojoEnum.isVoUpdate(type) ||
                    PojoEnum.isVoCriteria(type)) {
                code += PojoAnnotation.buildVo(leftTab, value, required, dataType, example);
            } else {
                code += PojoAnnotation.buildPoDto(leftTab, value);
            }

            code += leftTab + String.format("private %s %s;", dataType, fieldName);

            result.add(code);
            firstLine = false;
        }

        return StringUtils.join(result, "\n\n");
    }

    /**
     * <pre>
     * public Type get***() {
     *     return return ***;
     * }
     *
     * public void set***(Type ***) {
     *     this.%s = %s;
     * }
     *
     * ...
     * </pre>
     */
    public static String buildMethods(PojoEnum type, List<ColumnSchema> list) {
        AssertUtils.nonNull(type, "type");
        AssertUtils.nonNull(list, "list");

        List<String> result = new ArrayList<>();

        String leftTab = "\t";
        boolean firstLine = true;

        for (ColumnSchema c : list) {
            if (Check.isNull(c)) {
                continue;
            }

            if (PojoIgnore.isIgnore(type, c.getName())) {
                continue;
            }

            String javaSimpleWrapper = c.getJavaSimpleWrapper();
            String methodName = c.getCamelUpperFirst();
            String fieldName = c.getCamelLowerFirst();

            AssertUtils.nonEmpty(javaSimpleWrapper, "javaSimpleWrapper");
            AssertUtils.nonEmpty(methodName, "methodName");
            AssertUtils.nonEmpty(fieldName, "fieldName");

            String code = firstLine ? "" : leftTab;

            code += String.format("public %s get%s() {\n", javaSimpleWrapper, methodName) +
                    leftTab + String.format("\treturn %s;\n", fieldName) +
                    leftTab + "}\n\n";

            code += leftTab + String.format("public void set%s(%s %s) {\n", methodName, javaSimpleWrapper, fieldName) +
                    leftTab + String.format("\tthis.%s = %s;\n", fieldName, fieldName) +
                    leftTab + "}";

            result.add(code);
            firstLine = false;
        }

        return StringUtils.join(result, "\n\n");
    }

    /**
     * 布尔类型？
     */
    public static boolean guessBool(PojoEnum type, ColumnSchema column) {
        AssertUtils.nonNull(type, "type");
        AssertUtils.nonNull(column, "column");

        if (PojoEnum.isPo(type) || PojoEnum.isPoCriteria(type)) {
            return false;
        }

        String remark = column.getRemark();
        String javaSimpleWrapper = column.getJavaSimpleWrapper();
        if (Check.isEmpty(remark) || Check.isEmpty(javaSimpleWrapper)) {
            return false;
        }

        if (Integer.class.getSimpleName().equals(javaSimpleWrapper)) {
            return remark.contains(BOOL_MARK1) &&
                    remark.contains(BOOL_MARK2) &&
                    remark.contains(BOOL_MARK3);
        } else {
            return false;
        }
    }

}
