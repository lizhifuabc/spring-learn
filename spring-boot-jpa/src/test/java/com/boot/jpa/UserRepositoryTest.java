package com.boot.jpa;

import com.boot.jpa.domain.User;
import com.boot.jpa.repository.UserRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * UserDao
 *
 * @author lizhifu
 * @since  2021/1/4
 */
@SpringBootTest
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;
    @Test
    public void test() {
        User user = new User();
        user.setUserName("lizhifu3");
        userRepository.save(user);
        List<User> userList = userRepository.findByUserNameIsLike("%li%");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i).toString());
        }
        int i = userRepository.updateUserName("lizhifu3",1L);
        System.out.println(i);

        int j = userRepository.updateUserNameWithNative("lizhifu3",1L);
        System.out.println(j);
    }
}
