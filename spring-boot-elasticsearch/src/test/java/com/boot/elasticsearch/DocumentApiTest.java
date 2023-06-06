package com.boot.elasticsearch;

import com.boot.elasticsearch.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import jakarta.annotation.Resource;
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
    public void test() {
        User user = new User(1, "赵一", 18, "北京市昌平区", LocalDateTime.now());
        template.save(user);
        System.out.println("查询结果为："+template.get("1", User.class));
        System.out.println("删除结果为："+template.delete("1", User.class));
        saveList();
    }
    public void saveList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "赵1", 16, "北京市昌平区", LocalDateTime.now()));
        userList.add(new User(2, "钱2", 17, "北京市朝阳区", LocalDateTime.now()));
        userList.add(new User(3, "孙3", 18, "北京海淀", LocalDateTime.now()));
        userList.add(new User(4, "李4", 19, "江苏省盐城市", LocalDateTime.now()));
        userList.add(new User(5, "周5", 20, "江苏省苏州斯", LocalDateTime.now()));
        userList.add(new User(6, "吴6", 21, "江苏省南京市", LocalDateTime.now()));
        userList.add(new User(7, "郑7", 22, "北京市朝阳区", LocalDateTime.now()));
        userList.add(new User(8, "王8", 23, "山东济南", LocalDateTime.now()));
        userList.add(new User(9, "杨9", 24, "河南郑州", LocalDateTime.now()));
        userList.add(new User(10, "钟10", 25, "四川重庆", LocalDateTime.now()));
        Iterable<User> users = template.save(userList);
        for (User user : users) {
            System.out.println("批量插入结果："+user);
        }
    }
}
