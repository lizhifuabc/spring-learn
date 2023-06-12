package om.spring.boot.multitenant.column.person;

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
    private final PersonColumnRepository personDatabaseRepository;

    public IndexController(PersonColumnRepository personDatabaseRepository) {
        this.personDatabaseRepository = personDatabaseRepository;
    }

    @GetMapping("/")
    public String index() {
        personDatabaseRepository.save(new PerssonColumn().setName("lizhifu").setAge(18));
        return "hello";
    }
}
