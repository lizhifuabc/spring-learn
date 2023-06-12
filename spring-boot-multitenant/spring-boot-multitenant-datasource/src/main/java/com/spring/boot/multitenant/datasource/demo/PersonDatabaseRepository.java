package com.spring.boot.multitenant.datasource.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 仓储类
 *
 * @author lizhifu
 * @since 2023/6/12
 */
public interface PersonDatabaseRepository extends JpaRepository<PersonDatabase,Long> {
    List<PersonDatabase> findByName(String name);
}
