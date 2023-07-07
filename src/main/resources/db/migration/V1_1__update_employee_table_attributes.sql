CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE SEQUENCE employee_sequence START WITH 1;

CREATE TABLE employee
(
    id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    reference  VARCHAR,
    first_name VARCHAR,
    last_name  VARCHAR,
    birthdate  DATE
);
CREATE OR REPLACE FUNCTION set_employee_reference()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.reference := 'Employee-' || nextval('employee_sequence');
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_employee_reference_trigger
    BEFORE INSERT ON employee
    FOR EACH ROW
    EXECUTE FUNCTION set_employee_reference();