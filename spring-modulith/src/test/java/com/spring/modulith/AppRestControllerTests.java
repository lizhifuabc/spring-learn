package com.spring.modulith;

import com.spring.modulith.department.DepartmentDTO;
import com.spring.modulith.user.UserDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * rest controller 测试
 *
 * @author lizhifu
 * @since 2024/4/12
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppRestControllerTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void shouldAddNewUser() {
        UserDTO userDTO = new UserDTO(null, 1L, 1L, "Test", 30, "HR");
        ResponseEntity<UserDTO> responseEntity = restTemplate.postForEntity("/api/users",userDTO, UserDTO.class);
        assertNotNull(responseEntity.getBody());
        assertNotNull(userDTO.id());
    }

    @Test
    @Order(1)
    void shouldAddNewDepartment() {
        DepartmentDTO dep = new DepartmentDTO(null, 1L, "Test");
        dep = restTemplate.postForObject("/api/departments", dep, DepartmentDTO.class);
        assertNotNull(dep.id());
    }

    @Test
    @Order(2)
    void shouldFindDepartmentWithUsers() {
        DepartmentDTO dep = restTemplate.getForObject("/api/departments/{id}/with-users", DepartmentDTO.class, 1L);
        assertNotNull(dep);
        assertNotNull(dep.id());
    }
}
