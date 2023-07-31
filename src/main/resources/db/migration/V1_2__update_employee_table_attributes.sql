do
$$
    begin
        if not exists(select from pg_type where typname = 'sex') then
            create type sex as enum ('M','F');
        end if;
        if not exists(select from pg_type where typname = 'csp') then
            create type csp as enum ('M1','M2','OS1','OS2','OS3','OP1');
        end if;
    end
$$;
ALTER TABLE employee
    ADD COLUMN sex sex;
ALTER TABLE employee
    ADD COLUMN csp csp;
ALTER TABLE employee
    ADD COLUMN address varchar;
ALTER TABLE employee
    ADD COLUMN email_pro varchar
        constraint email_pro_unique unique;
ALTER TABLE employee
    ADD COLUMN email_perso varchar
        constraint email_perso_unique unique;
ALTER TABLE employee
    ADD COLUMN "role" varchar;
ALTER TABLE employee
    ADD COLUMN child_number integer;
ALTER TABLE employee
    ADD COLUMN hiring_date date;
ALTER TABLE employee
    ADD COLUMN departure_date date;
ALTER TABLE employee
    ADD COLUMN cnaps varchar;