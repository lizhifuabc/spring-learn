package com.boot.elasticsearch.repository;

import com.boot.elasticsearch.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRepository
 *
 * @author lizhifu
 * @date 2021/4/15
 */
@Repository
public interface UserRepository extends ElasticsearchRepository<User, Integer> {
    List<User> findUsersByNameAndAddress(String name, String address);
}
