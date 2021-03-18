-- ----------------------------
-- 测试表
-- ----------------------------
drop table if exists t_table_test;
create table t_table_test (
  id            bigint(20)      not null auto_increment  comment 'ID',
  info1 varchar(1) comment 'info1',
  info2 varchar(1) comment 'info2',
  info3 varchar(1) comment 'info3',
  info4 varchar(1) comment 'info4',
  primary key (id),
  unique key (info3),
  unique key (info4),
  unique key (info1,info2)
) engine=innodb CHARSET=utf8mb4 auto_increment=100 comment = '测试表';