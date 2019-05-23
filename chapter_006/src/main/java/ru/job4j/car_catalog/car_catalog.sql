--Create database
CREATE DATABASE car_store;

--Use database cars
\c car_store;

--Create tables car_body, engine, transmission, cars
CREATE TABLE car_body(
id serial primary key,
name varchar (50)
);
CREATE TABLE engine(
id serial primary key,
name varchar (50)
);
CREATE TABLE transmission(
id serial primary key,
name varchar (50)
);
CREATE TABLE cars(
car_ID serial primary key,
brand varchar (50),
model varchar (50),
car_body_ID int references car_body(id),
engine_ID int references engine(id),
transmission_ID int references transmission(id)
);

--Insert tables
INSERT INTO car_body(name)
VALUES
  ('coupe'),
  ('sedan'),
  ('hatchback'),
  ('wagon'),
  ('crossover'),
  ('minivan'),
  ('track'),
  ('limousine');
INSERT INTO engine(name)
VALUES ('diesel'), ('gas'), ('battery');
INSERT INTO transmission(name)
VALUES
  ('manual'),
  ('automatic'),
  ('robotic');
INSERT INTO cars(brand, model, car_body_ID, engine_ID, transmission_ID)
VALUES
('KIA', 'RIO', '2', '2', '1'),
('KIA', 'Cee''d', '3', '2', '2'),
('KIA', 'Sportage', '5', '1', '1'),
('KIA', 'Carens', '6', '2', '3'),
('KIA', 'Cerato', '1', '2', '2'),
('KIA', 'Cee''d_sw', '4', '2', '1');

--1. Вывести список всех машин и все привязанные к ним детали.
SELECT c.brand, c.model, b.name AS car_body, e.name AS engine, t.name AS transmission
FROM cars AS c
  INNER JOIN car_body AS b ON c.car_body_ID = b.id
  INNER JOIN engine AS e ON c.engine_ID = e.id
  INNER JOIN transmission AS t ON c.transmission_ID = t.id;
--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
--Не использованные кузова
SELECT b.name AS car_body_unused FROM cars AS c
  RIGHT OUTER JOIN car_body AS b on c.car_body_ID = b.id
  WHERE c.car_ID is null;
--Не использованные двигатели
SELECT e.name AS engine_unused FROM cars AS c
  RIGHT OUTER JOIN engine AS e on c.engine_ID = e.id
  WHERE c.car_ID is null;
--Не использованные коробки передач
SELECT t.name AS transmission_unused FROM cars AS c
  RIGHT OUTER JOIN transmission AS t on c.transmission_ID = t.id
  WHERE c.car_ID is null;