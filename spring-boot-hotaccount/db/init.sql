create schema my_db;
drop table if exists my_db.account;
create table my_db.account (
     id      bigint not null auto_increment comment 'ID',
     version bigint default 0 comment '版本号',
     account_no  bigint not null comment '账号',
     balance decimal(14,4) not null comment '账户余额',
     create_time datetime not null comment '创建时间',
     primary key (id),
     unique key (account_no)
) engine=innodb auto_increment=100 comment = '账户';

drop table if exists my_db.account_his;
create table my_db.account_his (
       id          bigint(20) not null auto_increment   comment 'ID',
       account_no  bigint(20) not null comment '账号',
       version bigint default 0 comment '版本号',
       amount      decimal(14,4) not null comment '发生额',
       trade_flow_no varchar(36) not null comment '交易流水号',
       trade_sign_no  bigint(20) not null comment '上游标识',
       trade_type  tinyint not null comment '交易类型',
       direction   tinyint not null comment '资金变动方向',
       b_balance   decimal(14,4)  comment '当前余额',
       a_balance   decimal(14,4)  comment '发生后余额',
       create_time datetime not null comment '创建时间',
       entry tinyint not null comment '是否入账',
       primary key (id),
       unique key (trade_flow_no,trade_sign_no)
) engine=innodb auto_increment=100 comment = '账户历史';
