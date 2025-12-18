/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 19:02:29 UTC+08:00
 *****************************************************/

create database if not exists data_collector_web charset utf8mb4 collate utf8mb4_general_ci;

create table if not exists tb_user(
    id integer primary key auto_increment,
    username varchar(255) unique not null,
    password varchar(512) not null,
    name varchar(32),
    birthday datetime,
    phone varchar(11),
    email varchar(128),
    created_at datetime default current_timestamp not null,
    updated_at datetime default current_timestamp on update current_timestamp not null,
    deleted boolean default false
);

insert into tb_user
    (id, username, password, name, birthday, phone, email)
values
    (1,'admin', '123456', '管理员', '2025-12-30 00:00:00', '133xxxxxxxx', 'example@example.com');

create table if not exists tb_user_group(
    id integer primary key auto_increment,
    name varchar(64) not null,
    description varchar(255),
    parent_id integer default 0,
    created_at datetime default current_timestamp not null,
    updated_at datetime default current_timestamp on update current_timestamp not null,
    deleted boolean default false
);

insert into tb_user_group
    (id, name, description)
values
    (1, '默认分组', '默认分组');

create table if not exists tb_user_group_relation(
    id integer primary key auto_increment,
    user_id integer not null,
    user_group_id integer not null,
    created_at datetime default current_timestamp not null,
    updated_at datetime default current_timestamp on update current_timestamp not null,
    deleted boolean default false
);

create table if not exists tb_user_role(
    id integer primary key auto_increment,
    name varchar(64) not null,
    code varchar(128) not null,
    description varchar(255),
    builtin boolean default true,
    created_at datetime default current_timestamp not null,
    updated_at datetime default current_timestamp on update current_timestamp not null,
    deleted boolean default false
)