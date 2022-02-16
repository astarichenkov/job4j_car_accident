CREATE DATABASE auto_crash;

CREATE TABLE accident
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(2000),
    text    TEXT,
    address VARCHAR(2000),
    type_id INT REFERENCES type (id)
);

CREATE TABLE type
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(2000)
);

create table accident_rule
(
    id          serial primary key,
    accident_id int references accident (id),
    rule_id     int references rule (id)
);

CREATE TABLE rule
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(2000)
);