package com.boot.p6spy.repository;

import com.boot.p6spy.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 城市
 *
 * @author lizhifu
 * @since 2023/7/17
 */
public interface CityRepository extends JpaRepository<City, Long> {

}
