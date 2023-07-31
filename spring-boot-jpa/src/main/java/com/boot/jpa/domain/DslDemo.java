package com.boot.jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 实体
 *
 * @author lizhifu
 * @since 2023/7/31
 */
@Entity
@Table(name = "dsl_demo")
@Getter
@Setter
public class DslDemo {
    /**
     * 主键生成采用数据库自增方式，比如MySQL的AUTO_INCREMENT
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dsl_name", nullable = false, length = 128, unique = true)
    private String dslName;

    @Column(name = "dsl_age", nullable = false)
    private int dslAge;

    @Column(name = "dsl_email", length = 64, unique = true)
    private String dslEmail;

    @Column(name = "create_time", nullable = false, length = 32)
    private String createTime = LocalDateTime.now().toString();
}
