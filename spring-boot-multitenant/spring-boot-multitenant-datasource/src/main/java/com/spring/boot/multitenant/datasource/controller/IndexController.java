package com.spring.boot.multitenant.datasource.controller;

import com.spring.boot.multitenant.datasource.demo.PersonDatabase;
import com.spring.boot.multitenant.datasource.demo.PersonDatabaseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * hello
 *
 * @author lizhifu
 * @since 2023/6/12
 */
@RestController
public class IndexController {
    private final PersonDatabaseRepository personDatabaseRepository;

    public IndexController(PersonDatabaseRepository personDatabaseRepository) {
        this.personDatabaseRepository = personDatabaseRepository;
    }

    @GetMapping("/")
    public String index() {
        List<PersonDatabase> lizhifu = personDatabaseRepository.findByName("lizhifu");
        return "hello";
    }
}
