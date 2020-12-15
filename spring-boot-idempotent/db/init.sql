create schema my_db;
drop table if exists my_db.db_idempotent;
create table my_db.db_idempotent (
    id        bigint(20)     not null auto_increment   comment 'ID',
    third_no  varchar(256) not null  default '' comment '第三方唯一编号',
    primary key (id),
    unique key (third_no)
) engine=innodb auto_increment=100 comment = '数据库幂等';

drop table if exists my_db.optimistic;
create table my_db.optimistic (
     id      bigint(20)     not null auto_increment   comment 'ID',
     status  varchar(256) not null  default '' comment '状态',
     version bigint not null default 0 comment 'version',
     primary key (id)
) engine=innodb auto_increment=100 comment = '乐观锁';