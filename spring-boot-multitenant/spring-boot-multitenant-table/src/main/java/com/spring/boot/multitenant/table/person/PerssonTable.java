package com.spring.boot.multitenant.table.person;

import jakarta.persistence.*;
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
@Table(name = "persson_table_${{tenant}}")
@Accessors(chain = true)
public class PerssonTable {
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
