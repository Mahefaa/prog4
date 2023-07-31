create table if not exists "company"
(
    id          uuid
        constraint enterprise_pk primary key default uuid_generate_v4(),
    name        varchar not null,
    description varchar not null,
    slogan      varchar not null,
    address     varchar not null,
    email       varchar not null,
    nif         varchar not null,
    stat        varchar not null,
    rcs         varchar not null,
    logo        varchar
);