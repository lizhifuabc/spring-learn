package com.boot.jpa.repository;

import com.boot.jpa.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 父
 *
 * @author lizhifu
 * @since 2023/7/24
 */
public interface ParentRepository extends JpaRepository<Parent, Long> {
}
