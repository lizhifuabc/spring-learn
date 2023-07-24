package com.boot.jpa.repository;

import com.boot.jpa.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 父对象
 *
 * @author lizhifu
 * @since 2023/7/24
 */
public interface ChildRepository extends JpaRepository<Child, Long> {
}
