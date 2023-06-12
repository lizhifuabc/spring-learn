package om.spring.boot.multitenant.column.person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 仓储类
 *
 * @author lizhifu
 * @since 2023/6/12
 */
public interface PersonColumnRepository extends JpaRepository<PerssonColumn,Long> {
    List<PerssonColumn> findByName(String name);
}
