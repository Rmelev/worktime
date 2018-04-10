CREATE DATABASE worktime;

CREATE TABLE roles (
  id serial PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE users (
  id serial PRIMARY KEY,
  login VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255),
  role_id INTEGER REFERENCES roles(id) NOT NULL
);

CREATE TABLE records (
  id serial PRIMARY KEY,
  user_id INTEGER NOT NULL,
  come TIMESTAMP DEFAULT now(),
  away TIMESTAMP,
  totaltime TIME,
  status BOOLEAN DEFAULT FALSE,
  fordate INTEGER NOT NULL DEFAULT extract(DAY FROM now())
);

CREATE TABLE breaks (
  id serial PRIMARY KEY,
  record_id INTEGER REFERENCES records(id) NOT NULL,
  out TIMESTAMP DEFAULT now(),
  comein TIMESTAMP
);

INSERT INTO roles(name) VALUES ('ROLE_ADMIN');
INSERT INTO roles(name) VALUES ('ROLE_USER');

INSERT INTO users (login, password, email, role_id) VALUES('musk', '123', 'musk@paypal', 1);
INSERT INTO users (login, password, email, role_id) VALUES('roman', '100000', 'roman@job', 2);



