ALTER TABLE employee
    ALTER COLUMN sex TYPE varchar USING sex::varchar;
ALTER TABLE employee
    ALTER COLUMN csp TYPE varchar USING csp::varchar;
DROP TYPE IF EXISTS sex;
DROP TYPE IF EXISTS csp;