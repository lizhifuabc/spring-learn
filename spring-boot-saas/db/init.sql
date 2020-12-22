create schema my_db;
drop table if exists my_db.mysql_account;
create table my_db.mysql_account (
   id      bigint not null auto_increment comment 'ID',
   tenant varchar(36) not null comment '租户信息',
   url varchar(256) not null comment 'URL',
   username varchar(36) not null comment '用户名',
   password varchar(36) not null comment '密码',
   primary key (id)
) engine=innodb auto_increment=100 comment = 'MYSQL账号信息';