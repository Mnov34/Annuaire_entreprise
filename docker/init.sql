-- Drop tables if they already exist (optional, for a clean initialization)
DROP TABLE IF EXISTS employees CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS services CASCADE;
DROP TABLE IF EXISTS sites CASCADE;

-- Create the 'sites' table
CREATE TABLE sites (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       site VARCHAR(255) NOT NULL
);

-- Create the 'services' table
CREATE TABLE services (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          service_name VARCHAR(255) NOT NULL
);

-- Create the 'users' table
CREATE TABLE users (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);

-- Create the 'employees' table with foreign keys to 'services' and 'sites'
CREATE TABLE employees (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           first_name VARCHAR(255) NOT NULL,
                           last_name VARCHAR(255) NOT NULL,
                           phone VARCHAR(255),
                           mobile_phone VARCHAR(255),
                           email VARCHAR(255) NOT NULL,
                           service_id INTEGER NOT NULL,
                           site_id INTEGER NOT NULL,
                           FOREIGN KEY (service_id) REFERENCES services(id),
                           FOREIGN KEY (site_id) REFERENCES sites(id)
);


-- Insert sample data into 'sites'
INSERT INTO sites (site) VALUES
                             ('Lille'),
                             ('Paris'),
                             ('Nantes'),
                             ('Toulouse'),
                             ('Nice');

-- Insert sample data into 'services'
INSERT INTO services (service_name) VALUES
                                        ('IT Support'),
                                        ('HR'),
                                        ('Finance');

-- Insert sample data into 'users'
INSERT INTO users (email, password) VALUES
                                           ('user1', 'password1'),
                                           ('user2', 'password2'),
                                           ('root', 'root');

-- Insert sample data into 'employees'
INSERT INTO employees (first_name, last_name, phone, mobile_phone, email, service_id, site_id) VALUES
                                                                                                   ('John', 'Doe', '1234567890', '0987654321', 'john.doe@example.com', 1, 1),
                                                                                                   ('Jane', 'Smith', '1111111111', '2222222222', 'jane.smith@example.com', 2, 2);
