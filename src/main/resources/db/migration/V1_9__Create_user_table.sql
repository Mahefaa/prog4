create table if not exists "user"
(
    id       uuid
        constraint user_pk primary key default uuid_generate_v4(),
    username varchar not null,
    password varchar not null
);