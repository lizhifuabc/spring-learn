package com.spring.boot.multitenant.schema.person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 仓储类
 *
 * @author lizhifu
 * @since 2023/6/12
 */
public interface PersonSchemaRepository extends JpaRepository<PerssonSchema,Long> {
    List<PerssonSchema> findByName(String name);
}
