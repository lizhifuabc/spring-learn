package com.boot.jpa.repository;

import com.boot.jpa.domain.DslDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * DslDemo
 *
 * @author lizhifu
 * @since 2023/7/31
 */
public interface DslDemoQuerydslRepository extends JpaRepository<DslDemo, Long>, QuerydslPredicateExecutor<DslDemo> {

}
