package com.ichings.generator.util;

import com.google.common.base.CaseFormat;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/**
 * 字符串
 *
 * @author 宋欢
 */
public final class StringUtils {
    /**
     * 默认的输出字符编码
     */
    private static final Charset DEFAULT_OUT_CHARSET = StandardCharsets.UTF_8;

    /**
     * 默认的输入字符编码
     */
    private static final Charset DEFAULT_IN_CHARSET = StandardCharsets.ISO_8859_1;

    private StringUtils() {
    }

    /**
     * 去掉左右空白
     *
     * @param s a String
     * @return Returns the cleaned value if it exists, or null
     */
    public static String trimSpace(String s) {
        return (s != null) ? s.trim() : null;
    }

    /**
     * 去掉左右字符
     *
     * @param s a String
     * @param c a char
     * @return Returns the cleaned value if it exists, or null
     */
    public static String trim(String s, char c) {
        if (s == null) {
            return null;
        }

        int len = s.length();
        int st = 0;
        char[] val = s.toCharArray();

        while ((st < len) && (val[st] == c)) {
            st++;
        }

        while ((st < len) && (val[len - 1] == c)) {
            len--;
        }

        return ((st > 0) || (len < s.length())) ? s.substring(st, len) : s;
    }

    /**
     * 全部小写
     *
     * @param s a String
     * @return Returns the lower case value if it exists, or null
     */
    public static String toLower(String s) {
        return (s != null) ? s.toLowerCase() : null;
    }

    /**
     * 全部小写
     *
     * @param s a String
     * @return Returns the upper case value if it exists, or null
     */
    public static String toUpper(String s) {
        return (s != null) ? s.toUpperCase() : null;
    }

    /**
     * 首字母大写
     *
     * @param s a String
     * @return Returns the first upper value if it exists, or null
     */
    public static String upperFirst(String s, boolean otherLower) {
        if (s == null) {
            return null;
        }

        if (s.isEmpty()) {
            return "";
        }

        String first = s.substring(0, 1);
        String other = s.substring(1);
        if (otherLower) {
            other = other.toLowerCase();
        }

        return first.toUpperCase() + other;
    }

    /**
     * 转换编码格式
     *
     * @param s       a String
     * @param charset a Charset, use Charset.forName(""), default StandardCharsets.ISO_8859_1
     * @return Returns the converted value if it exists, or null
     */
    public static String encoding(String s, Charset charset) {
        if (s == null) {
            return null;
        }

        if (charset == null) {
            charset = DEFAULT_IN_CHARSET;
        }

        return new String(s.getBytes(charset), DEFAULT_OUT_CHARSET);
    }

    /**
     * 列表 -> 字符串
     * use StringJoiner contain StringBuilder
     *
     * @param list      a Iterable
     * @param separator the delimiters
     * @return Returns the joined value if it exists, or null
     */
    public static <T> String join(Collection<T> list, String separator) {
        if (list == null) {
            return null;
        }

        StringJoiner result = new StringJoiner(separator);

        for (T t : list) {
            result.add(String.valueOf(t));
        }

        return result.toString();
    }

    /**
     * 字符串 -> 列表
     *
     * @param s         a String
     * @param separator the delimiters
     * @return Returns the List<String> if it exists, or null
     */
    public static List<String> split(String s, String separator) {
        if (s == null) {
            return null;
        }

        List<String> result = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(s, separator);
        while (tokenizer.hasMoreTokens()) {
            result.add(tokenizer.nextToken());
        }

        return result;
    }

    /**
     * 下划线 -> 驼峰式
     *
     * @param s          a String
     * @param upperFirst 首字母大写？
     * @return Returns the converted value if it exists, or null
     */
    public static String underscoreToCamel(String s, boolean upperFirst) {
        if (s == null) {
            return null;
        }

        return CaseFormat.LOWER_UNDERSCORE.to(upperFirst ? CaseFormat.UPPER_CAMEL : CaseFormat.LOWER_CAMEL, s);
    }

    /**
     * 下划线 -> 连接线
     *
     * @param s a String
     * @return Returns the converted value if it exists, or null
     */
    public static String underscoreToHyphen(String s) {
        if (s == null) {
            return null;
        }

        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, s);
    }

    /**
     * 驼峰式 -> 下划线
     *
     * @param s a String
     * @return Returns the converted value if it exists, or null
     */
    public static String camelToUnderscore(String s) {
        if (s == null) {
            return null;
        }

        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s);
    }

    /**
     * 驼峰式 -> 连接线
     *
     * @param s a String
     * @return Returns the converted value if it exists, or null
     */
    public static String camelToHyphen(String s) {
        if (s == null) {
            return null;
        }

        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, s);
    }

    /**
     * 连接线 -> 驼峰式
     *
     * @param s          a String
     * @param upperFirst 首字母大写？
     * @return Returns the converted value if it exists, or null
     */
    public static String hyphenToCamel(String s, boolean upperFirst) {
        if (s == null) {
            return null;
        }

        return CaseFormat.LOWER_HYPHEN.to(upperFirst ? CaseFormat.UPPER_CAMEL : CaseFormat.LOWER_CAMEL, s);
    }

    /**
     * 连接线 -> 下划线
     *
     * @param s a String
     * @return Returns the converted value if it exists, or null
     */
    public static String hyphenToUnderscore(String s) {
        if (s == null) {
            return null;
        }

        return CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_UNDERSCORE, s);
    }

    /**
     * 连接线、驼峰式 -> 下划线
     *
     * @param s a String
     * @return Returns the converted value if it exists, or null
     */
    public static String hyphenCamelToUnderscore(String s) {
        if (s == null) {
            return null;
        }

        return hyphenToUnderscore(camelToUnderscore(s));
    }

}
