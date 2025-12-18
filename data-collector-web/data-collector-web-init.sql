/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 19:02:29 UTC+08:00
 *****************************************************/

create database if not exists data_collector_web charset utf8mb4 collate utf8mb4_general_ci;
use data_collector_web;

-- 用户
create table if not exists tb_user
(
    id         integer primary key auto_increment,
    username   varchar(255) unique                                            not null,
    password   varchar(512)                                                   not null,
    name       varchar(32),
    birthday   datetime,
    phone      varchar(11),
    email      varchar(128),
    created_at datetime default current_timestamp                             not null,
    updated_at datetime default current_timestamp on update current_timestamp not null,
    deleted    boolean  default false
);

-- 用户组
create table if not exists tb_user_group
(
    id          integer primary key auto_increment,
    name        varchar(64)                                                    not null,
    description varchar(255),
    parent_id   integer  default 0,
    created_at  datetime default current_timestamp                             not null,
    updated_at  datetime default current_timestamp on update current_timestamp not null,
    deleted     boolean  default false
);

-- 用户角色
create table if not exists tb_user_role
(
    id          integer primary key auto_increment,
    name        varchar(64)                                                    not null,
    code        varchar(128)                                                   not null,
    description varchar(255),
    builtin     boolean  default true,
    created_at  datetime default current_timestamp                             not null,
    updated_at  datetime default current_timestamp on update current_timestamp not null,
    deleted     boolean  default false
);

-- 角色权限
create table if not exists tb_user_permission
(
    id          integer primary key auto_increment,
    code        varchar(128) unique                                            not null,
    name        varchar(64) unique                                             not null,
    description varchar(255),
    parent_id   integer                                                        not null,
    created_at  datetime default current_timestamp                             not null,
    updated_at  datetime default current_timestamp on update current_timestamp not null,
    deleted     boolean  default false
);

-- 用户<->用户组
create table if not exists tb_user_group_relation
(
    id            integer primary key auto_increment,
    user_id       integer                                                        not null,
    user_group_id integer                                                        not null,
    created_at    datetime default current_timestamp                             not null,
    updated_at    datetime default current_timestamp on update current_timestamp not null,
    deleted       boolean  default false
);

-- 用户<->角色
create table if not exists tb_user_role_relation
(
    id         integer primary key auto_increment,
    user_id    integer                                                        not null,
    role_id    integer                                                        not null,
    created_at datetime default current_timestamp                             not null,
    updated_at datetime default current_timestamp on update current_timestamp not null,
    deleted    boolean  default false
);

-- 角色<->权限
create table if not exists tb_user_role_permission_relation
(
    id            integer primary key auto_increment,
    rule_id       integer                                                        not null,
    permission_id integer                                                        not null,
    created_at    datetime default current_timestamp                             not null,
    updated_at    datetime default current_timestamp on update current_timestamp not null,
    deleted       boolean  default false
);

insert into tb_user (id, username, password, name, birthday, phone, email)
values (1, 'admin', '123456', '管理员', '2025-12-30 00:00:00', '133xxxxxxxx', 'example@example.com');

insert into tb_user_group (id, name, description)
values (1, '默认分组', '默认分组');

insert into tb_user_group_relation (id, user_id, user_group_id)
values (1, 1, 1);

insert into tb_user_role (id, name, code, description)
values (1, '系统管理员', 'system:admin', '系统管理员');

insert into tb_user_role_relation (id, user_id, role_id)
values (1, 1, 1);

insert into tb_user_permission (id, code, name, description, parent_id)
values (1, 'system:admin', '系统管理', '系统管理员', 0);

insert into tb_user_role_permission_relation (id, rule_id, permission_id)
values (1, 1, 1);