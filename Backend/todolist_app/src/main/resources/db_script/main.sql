CREATE DATABASE to_do_list_app;

USE to_do_list_app;

CREATE TABLE users(
    user_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    primary key (user_id)
)