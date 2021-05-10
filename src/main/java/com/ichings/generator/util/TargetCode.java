package com.ichings.generator.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 目标代码文件名
 *
 * @author 宋欢
 */
public final class TargetCode {
    /**
     * 类文件后缀
     */
    private static final String CLASS_SUFFIX = ".java";

    /**
     * 资源文件后缀
     */
    private static final String XML_SUFFIX = ".xml";

    /**
     * 匹配类、接口、枚举名
     */
    private static final Pattern PATTERN_CLASS_NAME = Pattern.compile("public\\s+(final\\s+)?(interface\\s+)?(class\\s+)?(enum\\s+)?(\\w+).*\\{");

    /**
     * 匹配XML名
     */
    private static final Pattern PATTERN_XML_NAME = Pattern.compile("xmlName=(\\w+)");

    /**
     * 解析文件名
     */
    public static String getName(File target) throws IOException {
        AssertUtils.nonNull(target, "target");

        String fileName = target.getName();
        AssertUtils.nonEmpty(fileName, "fileName");

        if (fileName.endsWith(CLASS_SUFFIX)) {
            return getClassName(target);
        }

        if (fileName.endsWith(XML_SUFFIX)) {
            return getXmlName(target);
        }

        return null;
    }

    /**
     * 从类代码，解析类文件名
     */
    public static String getClassName(File target) throws IOException {
        AssertUtils.nonNull(target, "target");

        List<String> lines = FileUtils.getLines(target);
        if (Check.isEmpty(lines)) {
            return null;
        }

        for (String code : lines) {
            if (Check.nonEmpty(code)) {
                Matcher m = PATTERN_CLASS_NAME.matcher(code);
                if (m.find()) {
                    return m.group(5) + CLASS_SUFFIX;
                }
            }
        }

        return null;
    }

    /**
     * 从XML首行，解析XML文件名
     */
    public static String getXmlName(File target) throws IOException {
        AssertUtils.nonNull(target, "target");

        String code = FileUtils.getChars(target);
        if (Check.isEmpty(code)) {
            return null;
        }

        int firstLfAt = code.indexOf("\n");
        String firstLine = code.substring(0, firstLfAt);
        if (Check.isEmpty(firstLine)) {
            return null;
        }

        Matcher m = PATTERN_XML_NAME.matcher(firstLine);
        if (m.find()) {
            code = code.substring(firstLfAt + 1);
            FileUtils.write(target, code, false);
            return m.group(1) + XML_SUFFIX;
        } else {
            return null;
        }
    }

}
