package com.boot.log.transactional;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 事务与 ApplicationEventPublisher 测试
 *
 * @author lizhifu
 * @since 2023/9/16
 */
@Entity
@Table(name = "t_account")
@Getter
@Setter
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",unique = true)
    private String name;
    private String password;
}
