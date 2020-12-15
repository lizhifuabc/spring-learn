package com.mybatis.common.util;

import com.mybatis.common.annotation.MapperXmlElement;
import com.mybatis.common.annotation.Transient;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 类相关
 *
 * @author lizhifu
 * @date 2020/12/14
 */
public class ClassUtils {
    /**
     * 判断是否是正常属性
     * 只要是private的属性，才可以被反
     * @param field
     * @return
     */
    public static boolean isNormal(final Field field) {
        int modifiers = field.getModifiers();
        return !Modifier.isTransient(modifiers) &&
                !field.isAnnotationPresent(Transient.class) &&
                !Modifier.isFinal(modifiers) &&
                !Modifier.isPublic(modifiers) &&
                !Modifier.isStatic(modifiers);
    }

    /**
     * 获取mapper继承的接口的所有MapperXmlElement注解
     * 目的是为了查看生成什么类型的sql
     * @param clazz
     * @return
     */
    public static List<MapperXmlElement> getMapperXmlElement(final Class<?> clazz) {
        Set<Class<?>> interfaces = new LinkedHashSet<Class<?>>(8);
        getAllInterfaces(interfaces, clazz);
        List<MapperXmlElement> mapperXmlElsRtn = new ArrayList<MapperXmlElement>(16);
        for (Class<?> it : interfaces) {
            for (MapperXmlElement mapperXmlEl : it.getAnnotationsByType(MapperXmlElement.class)) {
                mapperXmlElsRtn.add(mapperXmlEl);
            }
        }
        return mapperXmlElsRtn;
    }
    /**
     * 获取类所有的接口
     * @param interfaces
     * @param clazz
     */
    public static void getAllInterfaces(final Set<Class<?>> interfaces, final Class<?> clazz) {
        interfaces.add(clazz);
        for (Class<?> it : clazz.getInterfaces()) {
            getAllInterfaces(interfaces, it);
        }
    }
}
