package com.ichings.generator.tag;

/**
 * 属性注释
 *
 * @author 宋欢
 */
public final class PojoAnnotation {
    /**
     * Po
     * Dto
     */
    public static String buildPoDto(String leftTab, String value) {
        return "/**\n" +
                leftTab + String.format(" * %s\n", value) +
                leftTab + " */\n";
    }

    /**
     * Vo
     */
    public static String buildVo(String leftTab, String value, boolean required, String dataType, String example) {
        String result = String.format(
                "@ApiModelProperty(value = \"%s\", required = %s, dataType = \"%s\", example = \"%s\")\n",
                value, (required ? "true" : "false"), dataType, example);

        if (required) {
            result = String.format("@NotBlank(message = \"请填写%s\")\n", value) + leftTab + result;
        }

        return result;
    }

}
