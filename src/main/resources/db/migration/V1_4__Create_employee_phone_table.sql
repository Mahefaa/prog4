create table if not exists "employee_phone"
(
    id           uuid
        constraint employee_phone_pk primary key default uuid_generate_v4(),
    phone_number varchar not null
        constraint employee_phone_number_unique unique,
    employee_id  uuid,
    constraint fk_employee_phone foreign key (employee_id) references employee (id)
);

