-- auto-generated definition
create table admin
(
    userid   bigint  not null primary key,
    mobile   varchar(50) not null,
    password varchar(50) not null,
    name     varchar(50) null,
    email    varchar(50) null
)comment '管理员信息' charset = utf8;

-- auto-generated definition
create table t_admin
(
    userid   bigint  not null primary key,
    mobile   varchar(50) not null,
    password varchar(50) not null,
    name     varchar(50) null,
    email    varchar(50) null
)comment '管理员信息' charset = utf8;
