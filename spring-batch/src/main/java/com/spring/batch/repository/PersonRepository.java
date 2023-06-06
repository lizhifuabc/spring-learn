package com.spring.batch.repository;

import com.spring.batch.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * repository
 *
 * @author lizhifu
 * @since 2023/6/6
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
