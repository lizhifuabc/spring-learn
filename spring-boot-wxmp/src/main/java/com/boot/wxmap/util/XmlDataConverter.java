package com.boot.wxmap.util;

import com.thoughtworks.xstream.converters.basic.StringConverter;

/**
 * xml数据转换
 *
 * @author lizhifu
 * @date 2021/1/27
 */
public class XmlDataConverter extends StringConverter {
    @Override
    public String toString(Object obj) {
        return "<![CDATA[" + super.toString(obj) + "]]>";
    }
}
