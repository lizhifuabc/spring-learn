create schema my_db;
drop table if exists my_db.my_select;
create table my_db.my_select (
    select_id        bigint(20)     not null auto_increment   comment 'ID',
    order_no         varchar(256)   comment '单号',
    user_name        varchar(50)    default ''                comment '姓名',
    primary key (select_id)
) engine=innodb auto_increment=100 comment = '查询优化';