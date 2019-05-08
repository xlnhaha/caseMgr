CREATE DATABASE IF NOT EXISTS case_lmx DEFAULT CHARSET utf8;
use case_lmx;
drop table if exists person_info; create table person_info (id int PRIMARY KEY AUTO_INCREMENT not null , 
p_name varchar(10) not null,
p_age int not null,
p_sex varchar(5) not null,
phone varchar(20) not null,
p_addr_detail varchar(20) not null,
p_province varchar(10) not null,
p_municipal varchar(10) not null,
p_city varchar(10) not null,
p_area varchar(10) not null,
p_village varchar(10) not null,
p_pain_class int not null,
p_pain_long varchar(10) not null,
p_treat_times int not null,
p_treat_status int not null
);

drop table if EXISTS case_info; create table case_info (
id int PRIMARY KEY AUTO_INCREMENT not null,
pId int not null,
create_time datetime not null,
front_img blob,
back_img blob,
side_img blob,
zhusu varchar(200),
xbs varchar(200),
chati varchar(400),
zhenduan varchar(400),
treat_log varchar(800)
)


select * from ;