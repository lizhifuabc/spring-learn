package com.boot.elasticsearch;

import com.boot.elasticsearch.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DocumentApi
 *
 * @author lizhifu
 * @date 2021/4/15
 */
@SpringBootTest
public class DocumentApiTest {
    @Resource
    private ElasticsearchRestTemplate template;
    @Test
    public void save() {
        User user = new User(1, "李四", 22, "北京", LocalDateTime.now());
        template.save(user);
    }
    @Test
    public void get() {
        User user = template.get("1", User.class);
        System.out.println(user);
    }
    @Test
    public void delete() {
        String delete = template.delete("1", User.class);
        System.out.println(delete);
    }
    @Test
    public void saveList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "阿萨德", 16, "北京", LocalDateTime.now()));
        userList.add(new User(2, "阿斯顿发", 25, "山东", LocalDateTime.now()));
        userList.add(new User(3, "李四", 25, "北京昌平", LocalDateTime.now()));
        userList.add(new User(3, "王五", 25, "北朝阳", LocalDateTime.now()));
        Iterable<User> users = template.save(userList);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
