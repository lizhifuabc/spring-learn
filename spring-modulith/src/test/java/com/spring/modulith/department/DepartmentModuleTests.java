package com.spring.modulith.department;

import com.spring.modulith.event.OrganizationAddEvent;
import com.spring.modulith.event.OrganizationRemoveEvent;
import com.spring.modulith.department.repository.DepartmentRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

/**
 * 部门模块测试
 *
 * @author lizhifu
 * @since 2024/4/12
 */
@ApplicationModuleTest(ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentModuleTests {
    private static final long TEST_ID = 100;

    @Autowired
    DepartmentRepository repository;

    @Test
    @Order(1)
    void shouldAddDepartmentsOnEvent(Scenario scenario) {
        scenario.publish(new OrganizationAddEvent(TEST_ID))
                .andWaitForStateChange(() -> repository.findByOrganizationId(TEST_ID))
                .andVerify(result -> {assert !result.isEmpty();});
    }

    @Test
    @Order(2)
    void shouldRemoveDepartmentsOnEvent(Scenario scenario) {
        scenario.publish(new OrganizationRemoveEvent(TEST_ID))
                .andWaitForStateChange(() -> repository.findByOrganizationId(TEST_ID))
                .andVerify(result -> {assert result.isEmpty();});
    }
}
