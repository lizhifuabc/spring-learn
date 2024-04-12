package com.spring.modulith.user;

import com.spring.modulith.event.OrganizationRemoveEvent;
import com.spring.modulith.user.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

/**
 * 用户模块测试
 *
 * @author lizhifu
 * @since 2024/4/12
 */
@ApplicationModuleTest(ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserModuleTests {
    @Autowired
    UserRepository userRepository;

    @Test
    @Order(1)
    void shouldRemoveEmployeesOnEvent(Scenario scenario) {
        scenario.publish(new OrganizationRemoveEvent(1L))
                .andWaitForStateChange(() -> userRepository.count())
                .andVerify(result -> {assert result.intValue() == 0;});
    }
}
