CREATE TABLE IF NOT EXISTS "phone"
(
    id           uuid
        constraint phone_pk primary key default uuid_generate_v4(),
    phone_number varchar not null
        constraint phone_number_unique unique
);