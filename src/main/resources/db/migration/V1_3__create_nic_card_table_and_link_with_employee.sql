CREATE TABLE IF NOT EXISTS nic
(
    uuid          uuid
        constraint nic_pk primary key default uuid_generate_v4(),
    id            varchar unique not null,
    issuing_date  date           not null,
    issuing_place varchar        not null
);

ALTER TABLE employee
    ADD COLUMN nic_id uuid;
ALTER TABLE employee
    ADD CONSTRAINT fk_nic foreign key (nic_id) references nic (uuid);