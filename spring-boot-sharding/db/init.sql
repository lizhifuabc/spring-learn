drop database if exists my_db_0;
drop database if exists my_db_1;
create schema my_db_0;
create schema my_db_1;
drop table if exists my_db_0.t_order_0;
create table my_db_0.t_order_0 (
       id      bigint not null auto_increment comment '主键',
       version  bigint default 0 comment '版本号',
       user_id  BIGINT NOT NULL COMMENT '用户id',
       order_id BIGINT NOT NULL COMMENT '订单id',
       primary key (id)
) engine=innodb auto_increment=100 comment = '订单表0';

drop table if exists my_db_0.t_order_1;
create table my_db_0.t_order_1 (
       id      bigint not null auto_increment comment '主键',
       version  bigint default 0 comment '版本号',
       user_id  BIGINT NOT NULL COMMENT '用户id',
       order_id BIGINT NOT NULL COMMENT '订单id',
       primary key (id)
) engine=innodb auto_increment=100 comment = '订单表1';

drop table if exists my_db_1.t_order_0;
create table my_db_1.t_order_0 (
   id      bigint not null auto_increment comment '主键',
   version  bigint default 0 comment '版本号',
   user_id  BIGINT NOT NULL COMMENT '用户id',
   order_id BIGINT NOT NULL COMMENT '订单id',
   primary key (id)
) engine=innodb auto_increment=100 comment = '订单表0';

drop table if exists my_db_1.t_order_1;
create table my_db_1.t_order_1(
   id      bigint not null auto_increment comment '主键',
   version  bigint default 0 comment '版本号',
   user_id  BIGINT NOT NULL COMMENT '用户id',
   order_id BIGINT NOT NULL COMMENT '订单id',
   primary key (id)
) engine=innodb auto_increment=100 comment = '订单表1';