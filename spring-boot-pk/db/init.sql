create schema my_db;
drop table if exists my_db.id_gen;
create table my_db.id_gen (
     id bigint(20) not null  auto_increment comment 'ID',
     order_no varchar(256) comment '业务流水号',
     primary key (id)
) engine=innodb auto_increment=100 comment = '主键生成';
INSERT INTO my_db.id_gen (id, order_no) VALUES (100, '1');