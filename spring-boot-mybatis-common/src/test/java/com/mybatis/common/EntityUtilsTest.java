package com.mybatis.common;

import com.mybatis.common.xml.ColumnXml;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * EntityUtils
 *
 * @author lizhifu
 * @date 2020/12/15
 */
public class EntityUtilsTest {
    public static void main(String[] args) {
        MyInfo myInfo = new MyInfo();
        myInfo.setName("lzf");
        Field field = ReflectionUtils.findField(MyInfo.class,"name");
        field.setAccessible(true);
        Object ob = ReflectionUtils.getField(field, myInfo);
        ColumnXml.getColumnListValue(myInfo);
    }
    static class MyInfo{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
