-- Drop tables if they already exist (optional, for a clean initialization)
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS sites;

-- Create the 'sites' table
CREATE TABLE sites
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    site VARCHAR(255) NOT NULL UNIQUE
);

-- Create the 'department' table
CREATE TABLE department
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(255) NOT NULL UNIQUE
);

-- Create the 'users' table
CREATE TABLE users
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(255) NOT NULL
);

-- Create the 'employees' table with foreign keys to 'department' and 'sites'
CREATE TABLE employees
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    phone         VARCHAR(255),
    mobile_phone  VARCHAR(255),
    email         VARCHAR(255) NOT NULL,
    department_id INTEGER      NOT NULL,
    site_id       INTEGER      NOT NULL,
    FOREIGN KEY (department_id) REFERENCES department (id),
    FOREIGN KEY (site_id) REFERENCES sites (id)
);


-- Insert sample data into 'sites'
INSERT INTO sites (site)
VALUES ('Lille'),
       ('Paris'),
       ('Nantes'),
       ('Toulouse'),
       ('Nice');

-- Insert sample data into 'department'
INSERT INTO department (department_name)
VALUES ('Comptabilité'),
       ('production'),
       ('accueil'),
       ('informatique'),
       ('commercial');

-- Insert sample data into 'users'
INSERT INTO users (email, password)
VALUES ('user1', 'password1'),
       ('user2', 'password2'),
       ('root', 'root');

-- Insert sample data into 'employees'
INSERT INTO employees (first_name, last_name, phone, mobile_phone, email, site_id, department_id)
VALUES ('Jean', 'Dupont', '0102030405', '0607080910', 'jean.dupont@example.com', 1, 1),
       ('Marie', 'Curie', '0102030406', '0607080911', 'marie.curie@example.com', 3, 2),
       ('Pierre', 'Durand', '0102030407', '0607080912', 'pierre.durand@example.com', 5, 4),
       ('Sophie', 'Lefevre', '0102030408', '0607080913', 'sophie.lefevre@example.com', 4, 5),
       ('Luc', 'Martin', '0102030409', '0607080914', 'luc.martin@example.com', 2, 3);