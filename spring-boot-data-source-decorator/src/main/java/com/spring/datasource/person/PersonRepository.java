package com.spring.datasource.person;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 人员信息
 *
 * @author lizhifu
 * @since 2024/4/10
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
