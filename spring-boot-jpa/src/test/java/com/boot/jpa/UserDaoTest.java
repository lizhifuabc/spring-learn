package com.boot.jpa;

import com.boot.jpa.domain.User;
import com.boot.jpa.repository.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserDao
 *
 * @author lizhifu
 * @date 2021/1/4
 */
@SpringBootTest
public class UserDaoTest {
    @Resource
    private UserDao userDao;
    @Test
    public void test() {
        User user = new User();
        user.setUserName("lizhifu3");
        userDao.save(user);
        List<User> userList = userDao.findByUserNameIsLike("%li%");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i).toString());
        }
        int i = userDao.updateUserName("lizhifu3",1L);
        System.out.println(i);

        int j = userDao.updateUserNameWithNative("lizhifu3",1L);
        System.out.println(j);
    }
}
