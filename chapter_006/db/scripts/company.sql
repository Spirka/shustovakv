--Create table Company
CREATE TABLE company(
ID integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY(id)
);
--Create table person
CREATE TABLE person(
ID integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_key primary key(ID)
);
--Insert into tables company and person
INSERT INTO company(id, name)
VALUES
(1, 'GOOGLE'),
(2, 'MICROSOFT'),
(3, 'YAHOO'),
(4, 'APPLE'),
(5, 'YANDEX');
INSERT INTO person(id, name, company_id)
VALUES
(1, 'Mark', 1),
(2, 'Marta', 1),
(3, 'Minny', 2),
(4, 'Mads', 2),
(5, 'Kristian', 3),
(6, 'Mona', 3),
(7, 'Lisabeth', 4),
(8, 'Mia', 4),
(9, 'Margaret', 5),
(10, 'Martin', 5),
(11, 'Morten', 1);
--Retrieve in a single query:
-- -names of all persons that are NOT in the company with id = 5
-- -company name for each person
SELECT p.name, c.name FROM company AS c
JOIN person AS p ON c.id!=5 AND c.id = p.company_id;
--Select the name of the company with the maximum number of persons + number of persons in this company
SELECT c.name, count(p.company_id)
FROM company c JOIN person p ON c.id = p.company_id
GROUP BY c.name
ORDER BY count(p.ID) desc limit 1;
