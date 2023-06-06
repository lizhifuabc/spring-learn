package com.boot.elasticsearch;

import com.boot.elasticsearch.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;

import jakarta.annotation.Resource;

/**
 * 索引相关操作
 *
 * @author lizhifu
 * @date 2021/4/15
 */
@SpringBootTest
public class IndexApiTest {
    @Resource
    private ElasticsearchRestTemplate template;
    /**
     * 索引相关操作
     */
    @Test
    public void index() {
        IndexOperations indexOperations = template.indexOps(User.class);
        System.out.println("索引是否存在：" + indexOperations.exists());
        System.out.println("索引是否删除：" + indexOperations.delete());
        System.out.println("索引创建结果：" + indexOperations.create());
        System.out.println("映射修改结果：" + indexOperations.putMapping());
    }
}
