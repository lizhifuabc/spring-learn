package com.boot.gen.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成
 *
 * @author lizhifu
 * @date 2020/12/7
 */
public class GenUtils {
    /**
     * 获取模板信息
     * @return 模板列表
     */
    private List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add("vm/java/domain.java.vm");
        return templates;
    }
}
