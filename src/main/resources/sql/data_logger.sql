# oracle DDL ?
drop sequence seq_data_item ;
drop table data_logger ;

create sequence seq_data_item ;
create table data_logger (
 id number(19,0) not null primary key,
 capture_date_time timestamp,
 location varchar2(32),
 data_name varchar2(32),
 data_type varchar2(32),
 data_value decimal (7,2)
) ;

# add constraint to the table

# mysql

drop table data_logger ;

create table data_logger (
 id bigint not null primary key auto_increment,
 capture_date_time datetime,
 location varchar(32),
 data_name varchar(32),
 data_type varchar(32),
 data_value decimal (7,2)
) ;
