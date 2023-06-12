package com.spring.boot.multitenant.table.person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 仓储类
 *
 * @author lizhifu
 * @since 2023/6/12
 */
public interface PersonTableRepository extends JpaRepository<PerssonTable,Long> {
    List<PerssonTable> findByName(String name);
}
