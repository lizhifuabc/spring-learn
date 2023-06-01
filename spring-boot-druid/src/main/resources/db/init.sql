create schema if not exists my_db1;
drop table if exists my_db1.my_info;
create table my_db1.my_info (
    info_id        bigint(20)     not null auto_increment   comment 'ID',
    user_name      varchar(50)    default ''                comment '姓名',
    primary key (info_id)
) engine=innodb auto_increment=100 comment = '我的表';


create schema if not exists my_db2;
drop table if exists my_db2.my_info;
create table my_db2.my_info (
   info_id        bigint(20)     not null auto_increment   comment 'ID',
   user_name      varchar(50)    default ''                comment '姓名',
   primary key (info_id)
) engine=innodb auto_increment=100 comment = '我的表';