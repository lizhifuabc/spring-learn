-- auto-generated definition
create table my_info
(
    info_id   bigint auto_increment comment 'ID'
        primary key,
    user_name varchar(50) default '' null comment '姓名'
)
    comment '我的表';

