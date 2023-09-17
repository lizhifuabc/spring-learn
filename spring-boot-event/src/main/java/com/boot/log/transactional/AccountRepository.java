package com.boot.log.transactional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO
 *
 * @author lizhifu
 * @since 2023/9/16
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
