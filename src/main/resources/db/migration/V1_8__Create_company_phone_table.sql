create table if not exists "company_phone"
(
    id           uuid
        constraint company_phone_pk primary key default uuid_generate_v4(),
    phone_number_id uuid,
    constraint fk_company_phone_phone_id foreign key (phone_number_id) references phone (id),
    company_id  uuid,
    constraint fk_company_phone_company_id foreign key (company_id) references company (id)
);