package com.mybatis.common.util;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Donghuang
 * @date Oct 31, 2014 1:27:57 PM
 */
public class StrUtils {
    public static final String EMPTY = "";
    private static final int STRING_BUILDER_SIZE = 256;

    /**
     * split Camel Case
     * <pre>
     * nice - [nice]
     * World - [World]
     * MySQL - [My SQL]
     * HTML - [HTML]
     * JSONObject - [JSON Object]
     * JPanel - [J Panel]
     * toJSONString - [to JSON String]
     * Log4j - [Log4j]
     * 99Roses - [99 Roses]
     * DO178 - [DO178]
     * Do178 - [Do178]
     * </pre>
     * @param str word
     * @return split result
     */
    public static String[] splitCamel(final String str) {
        return StrUtils.isNotBlank(str) ?
                    // JSONObject - JSON Object
                    // 99Rose - 99 Rose
                str.split(new StringBuilder("(?<=[0-9A-Z])(?=[A-Z][a-z])")
                    // MySQL - My SQL
                    .append("|(?<=[a-z])(?=[A-Z])")
                    .toString()) :
                new String[0];
    }

    /**
     * FooBar - foo_bar
     * @param str FooBar
     * @return foo_bar
     */
    public static String camelToUnderscore(final String str) {
        return join(splitCamel(str), "_").toLowerCase();
    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    public static String join(final Iterable<?> iterable, final String separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }

    public static String join(final Iterator<?> iterator, final String separator) {

        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return Objects.toString(first, "");
        }

        // two or more elements
        // Java default is 16, probably too small
        StringBuilder buf = new StringBuilder(STRING_BUILDER_SIZE);
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    public static String join(final Object[] array, String separator) {
        return join(array, separator, 0, array.length);
    }

    public static String join(final Object[] array, String separator, final int startIndex, final int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY;
        }

        // endIndex - startIndex > 0:   Len = NofStrings *(len(firstString) + len(separator))
        //           (Assuming that all Strings are roughly equally long)
        int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }

        StringBuilder buf = new StringBuilder(noOfItems * 16);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    public static String uncapitalize(final String str) {
        return processFirstChar(str, Character::toLowerCase);
    }

    public static String capitalize(final String str) {
        return processFirstChar(str, Character::toTitleCase);
    }

    static String processFirstChar(final String str, final Function<Integer, Integer> fn) {
        final int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        int firstCodepoint = str.codePointAt(0);
        int newCodePoint = fn.apply(firstCodepoint);

        if (firstCodepoint == newCodePoint) {
            // same as original
            return str;
        }

        // cannot be longer than the char array
        int[] newCodePoints = new int[strLen];
        int outOffset = 0;
        // copy the first codepoint
        newCodePoints[outOffset++] = newCodePoint;
        for (int inOffset = Character.charCount(firstCodepoint); inOffset < strLen; ) {
            int codepoint = str.codePointAt(inOffset);
            // copy the remaining ones
            newCodePoints[outOffset++] = codepoint;
            inOffset += Character.charCount(codepoint);
        }
        return new String(newCodePoints, 0, outOffset);
    }
}
