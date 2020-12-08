create schema my_db;
drop table if exists my_db.my_info;
create table my_db.my_info (
    info_id        bigint(20)     not null auto_increment   comment 'ID',
    user_name      varchar(50)    default ''                comment '姓名',
    primary key (info_id)
) engine=innodb auto_increment=100 comment = '我的表';

drop table if exists my_db.my_code;
create table my_db.my_code (
   info_id        bigint(20)     not null auto_increment   comment 'ID',
   user_name      varchar(50)    default ''                comment '姓名',
   code     varchar(64)     not null                  comment '编码',
   amount decimal(14,2) comment '金额',
   create_time    datetime  comment '创建时间',
   primary key (info_id)
) engine=innodb auto_increment=100 comment = '代码生成测试';