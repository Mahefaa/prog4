INSERT INTO phone(phone_number)
SELECT DISTINCT (employee_phone.phone_number)
FROM employee_phone;

ALTER TABLE employee_phone
    ADD COLUMN phone_number_id uuid;

ALTER TABLE employee_phone
    ADD CONSTRAINT fk_phone_number foreign key (phone_number_id) references phone (id);

UPDATE employee_phone epn
SET phone_number_id = p.id
FROM phone p
WHERE epn.phone_number = p.phone_number;

ALTER TABLE employee_phone DROP CONSTRAINT employee_phone_number_unique;
ALTER TABLE employee_phone DROP COLUMN phone_number;
