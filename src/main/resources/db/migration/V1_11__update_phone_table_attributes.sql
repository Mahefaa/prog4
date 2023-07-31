ALTER TABLE phone
    ADD COLUMN code varchar;
ALTER TABLE phone
    ADD CONSTRAINT code_number_unique unique (code, phone_number);