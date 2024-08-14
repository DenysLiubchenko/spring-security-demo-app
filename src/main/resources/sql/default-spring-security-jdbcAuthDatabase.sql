create database security_demo_app;

use security_demo_app;

create table users
(
    id       bigint       not null auto_increment primary key,
    username varchar(100) not null,
    password varchar(100) not null,
    enabled  int          not null
);

create table authorities
(
    id        bigint       not null auto_increment primary key,
    username  varchar(100) not null,
    authority varchar(100) not null
);

create index users_username_idx on users (username);
create index users_username_idx on authorities (username);

insert ignore into users (username, password, enabled)
values ('John', '12345', '1');
insert ignore into authorities (username, authority)
values ('John', 'write');