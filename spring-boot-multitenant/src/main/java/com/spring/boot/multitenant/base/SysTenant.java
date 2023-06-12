package com.spring.boot.multitenant.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;

/**
 * 多租户
 * @author lizhifu
 */
@Entity
@Table(name = "t_sys_tenant")
@Accessors(chain = true)
public class SysTenant {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Comment("租户主键")
    @Getter
    private Long id;

    @Column(name = "tenant_id", length = 64, unique = true,nullable = false)
    @Getter
    @Setter
    private String tenantId;

    @Column(name = "username", length = 100,nullable = false)
    @Comment("数据库用户名")
    @Getter
    @Setter
    private String username;

    @Column(name = "password", length = 100,nullable = false)
    @Comment("数据库密码")
    @Getter
    @Setter
    private String password;

    @Column(name = "url", length = 1000,nullable = false)
    @Comment("数据库连接")
    @Getter
    @Setter
    private String url;
    @Column(name = "driver_class_name", length = 100,nullable = false)
    @Comment("数据库驱动")
    @Getter
    @Setter
    private String driverClassName;
}
