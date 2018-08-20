SET SCHEMA 'bank_app';

CREATE TABLE users
(
  user_id SERIAL PRIMARY KEY,
  username VARCHAR(20),
  password VARCHAR(20),
  firstname VARCHAR(20),
  lastname VARCHAR(20),
  age INTEGER,
  checking_balance VARCHAR(20),
  savings_balance VARCHAR(20)
);

CREATE TABLE transactions
(
  t_id SERIAL PRIMARY KEY,
  receipt VARCHAR(150),
  u_id INTEGER,
 t_date TIMESTAMP default now()
);