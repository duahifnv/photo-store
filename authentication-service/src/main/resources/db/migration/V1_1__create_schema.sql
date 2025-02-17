create table public.users
(
    username varchar(255) not null primary key,
    email    varchar(255) not null unique,
    password varchar(255) not null,
    role     varchar(100) not null,
    name varchar(100),
    surname varchar(100)
);
