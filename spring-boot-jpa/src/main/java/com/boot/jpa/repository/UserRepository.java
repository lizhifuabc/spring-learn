package com.boot.jpa.repository;

import com.boot.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserDao
 *
 * @author lizhifu
 * @date 2021/1/4
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByUserNameIsLike(String userName);

    /**
     * JPQL更新数据
     * @param userName
     * @param id
     * @return
     */
    @Modifying
    @Transactional
    @Query("update User a set a.userName = ?1 where a.id = ?2")
    Integer updateUserName(String userName,Long id);

    /**
     * 原生sql
     * @param userName
     * @param id
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "update my_db.my_user a set a.user_name = ?1 where a.id = ?2",nativeQuery = true)
    Integer updateUserNameWithNative(String userName,Long id);
}
