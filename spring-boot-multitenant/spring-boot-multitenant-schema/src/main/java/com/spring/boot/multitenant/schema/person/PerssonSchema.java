package com.spring.boot.multitenant.schema.person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.TenantId;

/**
 * 人员
 *
 * @author lizhifu
 * @since 2023/6/12
 */
@Entity
@Accessors(chain = true)
public class PerssonSchema {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Integer age;
    @TenantId
    @Getter
    private String tenantId;
}
