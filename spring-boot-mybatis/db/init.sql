create schema my_db;
drop table if exists my_db.my_info;
create table my_db.my_info (
    info_id        bigint(20)     not null auto_increment   comment 'ID',
    user_name      varchar(50)    default ''                comment '姓名',
    primary key (info_id)
) engine=innodb auto_increment=100 comment = '我的表';