package com.spring.boot.multitenant.table.person;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hello
 *
 * @author lizhifu
 * @since 2023/6/12
 */
@RestController
public class IndexController {
    private final PersonTableRepository personDatabaseRepository;

    public IndexController(PersonTableRepository personDatabaseRepository) {
        this.personDatabaseRepository = personDatabaseRepository;
    }

    @GetMapping("/")
    public String index() {
        personDatabaseRepository.save(new PerssonTable().setName("lizhifu").setAge(18));
        return "hello";
    }
}
