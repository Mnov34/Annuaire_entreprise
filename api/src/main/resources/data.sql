-- sample.sql
-- Inserts for the 'users' table including phone numbers

INSERT INTO users (username, password, firstName, lastName, site, phone)
VALUES ('alice', 'alice@', 'alice', 'roth', 'Paris', '555-0101'),
       ('bob', 'bob@', 'bob', 'roth', 'Paris', '555-0102'),
       ('carol', 'carol@', 'carol', 'roth', 'Nice', '555-0103'),
       ('dave', 'dave@', 'dave', 'roth', 'Lille', '555-0104'),
       ('eve', 'eve@', 'eve', 'roth', 'Toulouse', '555-0105'),
       ('root', 'root', 'root', 'admin', 'Nantes', '555-3105');

-- 2) Insert sites (unique 'city')
INSERT INTO site (city)
VALUES ('Paris'),
       ('Nantes'),
       ('Toulouse'),
       ('Nice'),
       ('Lille')
ON CONFLICT (city) DO NOTHING;

-- 3) Insert services (unique 'nomService')
INSERT INTO service (nomService)
VALUES ('Production'),
       ('Comptabilite'),
       ('Informatique')
ON CONFLICT (nomService) DO NOTHING;
