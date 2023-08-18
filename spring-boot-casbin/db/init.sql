CREATE TABLE IF NOT EXISTS `policy`
(
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `code`         varchar(255) NOT NULL COMMENT '编号',
    `sub`          varchar(255) NOT NULL COMMENT '请求',
    `obj`          varchar(255) NOT NULL COMMENT '请求资源',
    `act`          varchar(255) NOT NULL COMMENT '请求类型',
    `gmt_created`  datetime(0)  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime(0)  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uniq_code` (`code`) USING BTREE COMMENT '请求编号'
)
    ENGINE = InnoDB
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci
    COMMENT = '资源表';

CREATE TABLE IF NOT EXISTS `role`
(
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `code`         varchar(255) NOT NULL COMMENT '权限编号',
    `name`         varchar(255) NOT NULL COMMENT '名称',
    `sub`          varchar(255) NOT NULL COMMENT '请求',
    `obj`          varchar(255) NOT NULL COMMENT '资源',
    `act`          varchar(255) NOT NULL COMMENT '类型',
    `gmt_created`  datetime(0)  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime(0)  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uniq_code` (`code`) USING BTREE COMMENT '唯一索引'
)
    ENGINE = InnoDB
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci
    COMMENT = '角色表';

INSERT INTO `role`(`id`, `code`, `name`, `sub`, `obj`, `act`, `gmt_created`, `gmt_modified`) VALUES (1, 'RO2021001', '普通用户', 'ordinary', '/swagger-ui.html', '(GET)|(POST)|(HEAD)|(DELETE)|(PUT)', '2021-05-30 21:18:33', '2021-05-30 21:18:33');
INSERT INTO `role`(`id`, `code`, `name`, `sub`, `obj`, `act`, `gmt_created`, `gmt_modified`) VALUES (2, 'RO2021002', '超级管理员', 'admin', '/*', '(GET)|(POST)|(PUT)|(PATCH)|(DELETE)', '2021-05-31 10:14:14', '2021-05-31 10:14:14');

CREATE TABLE IF NOT EXISTS `user_role`
(
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `code`         varchar(255) NOT NULL COMMENT '用户编号',
    `username`     varchar(255) NOT NULL COMMENT '用户名',
    `password`     varchar(255) NOT NULL COMMENT '密码',
    `status`       tinyint(1)   NOT NULL DEFAULT 0 COMMENT '启用禁用 0启用 1禁用',
    `role_code`    varchar(255) NOT NULL COMMENT '权限编号',
    `gmt_created`  datetime(0)  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime(0)  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uniq_username` (`username`) USING BTREE COMMENT '唯一索引',
    UNIQUE INDEX `uniq_code` (`code`) USING BTREE COMMENT '唯一索引'
)
    ENGINE = InnoDB
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci
    COMMENT = '用户角色表';

CREATE TABLE IF NOT EXISTS `role_policy`
(
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `role_code`    varchar(255) NOT NULL COMMENT '权限编号',
    `policy_code`  varchar(255) NOT NULL COMMENT '资源编号',
    `gmt_created`  datetime(0)  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime(0)  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
)
    ENGINE = InnoDB
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci
    COMMENT = '角色资源表';