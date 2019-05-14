--Create database
CREATE DATABASE shop;

--Use db shop
\c shop;

--Create tables Products and Types
CREATE TABLE Types(
  Type_ID serial primary key,
  Name varchar(50)
);
CREATE TABLE Products(
  Product_ID serial primary key,
  Name varchar(50) NOT NULL,
  Type_ID int references Types(Type_ID),
  Expired_date date NOT NULL,
  Price numeric(5, 2)
);

--Fill tables
--Insert Types
INSERT INTO Types(Name)
VALUES ('CHEESE'), ('MILK'),('MEAT'), ('FISH');
INSERT INTO Products(Name, Type_ID, Expired_date, Price)
VALUES
  ('Maasdam', '1', '2019-12-01', 700.00),
  ('Ricotta', '1', '2019-05-01', 450.00),
  ('Parmesan', '1', '2020-06-01', 990.00),
  ('French Ice Creams', '2', '2019-06-05', 210.00),
  ('Light Ice Creams', '2', '2019-06-01', 175.00),
  ('No sugar Ice Creams', '2', '2019-05-25', 170.00),
  ('Milk', '2', '2019-05-17', 69.99),
  ('Beef', '3', '2019-05-20', 570.00),
  ('Pork', '3', '2019-05-21', 600.00),
  ('Mackerel', '4', '2019-06-01', 321.99);

--1. Написать запрос получение всех продуктов с типом "CHEESE"
SELECT p.name, p.product_ID, p.expired_date, p.price, t.name as type
FROM Products as p JOIN Types as t
    ON p.type_ID = t.type_ID
WHERE t.Name = 'CHEESE';
--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT * FROM Products WHERE Name LIKE '%Ice Creams%';
--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце
SELECT * FROM Products AS p
WHERE p.Expired_date BETWEEN '2019-06-01' AND '2019-06-30';
--4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM Products AS p
  INNER JOIN Types AS t on p.Type_ID = t.Type_ID
WHERE p.price = (SELECT max(p.price) FROM Products AS p);
--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT t.Name, count(p.Product_ID)
FROM Products AS p JOIN Types AS t
  ON p.Type_ID = t.Type_ID
GROUP BY t.Name;
--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT p.Name, p.Product_ID, p.Expired_date, t.Name AS type, p.Price
FROM Products AS p JOIN Types AS t on p.Type_ID = t.Type_ID
WHERE t.Name IN('CHEESE', 'MILK');
--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 3 штук
SELECT t.Name AS type, count(p.Name) AS count_of_products
FROM Products AS p JOIN Types AS t on p.Type_ID = t.Type_ID
GROUP BY t.Name
HAVING count(p.Name) < 4
ORDER BY t.Name;
--8. Вывести все продукты и их тип.
SELECT p.Name, t.Name AS type
FROM Products AS p JOIN Types AS t on p.Type_ID = t.Type_ID;