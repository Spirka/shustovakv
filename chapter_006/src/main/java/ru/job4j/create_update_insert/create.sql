--Create database
CREATE DATABASE task_1093;

--Use task_1093
\c task_1093;

--Create tables
CREATE TABLE Roles(
Role_ID serial primary key,
name varchar (50)
);

CREATE TABLE Permissions(
Permission_ID serial primary key,
name varchar (50)
);

CREATE TABLE Roles_permissions(
ID serial primary key,
Role_ID int references Roles(Role_ID),
Permission_ID int references Permissions(Permission_ID)
);

CREATE TABLE Users(
User_ID serial primary key,
Role_ID int references Roles(Role_ID),
first_name varchar (50),
last_name varchar (50),
phone_number varchar (20)
);

CREATE TABLE Category(
Category_ID serial primary key,
name varchar (50)
);

CREATE TABLE States(
State_ID serial primary key,
name varchar (50)
);

CREATE TABLE Items(
Item_ID serial primary key,
User_ID int references Users(User_ID),
Category_ID int references Category(Category_ID),
State_ID int references States(State_ID),
name varchar (50)
);

CREATE TABLE Comments(
Comment_ID serial primary key,
text text,
Item_ID int references Items(Item_ID)
);

CREATE TABLE Attachments(
Attachment_ID serial primary key,
file_name varchar (50),
Item_ID int references Items(Item_ID)
);

--Fill tables
--Insert Roles, Permissions and Roles_permissions
INSERT INTO Roles(name) VALUES ('Customer'), ('Admin');
INSERT INTO Permissions(name) VALUES ('Create'), ('Delete'), ('Update');
INSERT INTO Roles_permissions(Role_ID, Permission_ID) VALUES ('1', '1'), ('1', '3'), ('2', '2');
--Insert Users
INSERT INTO Users(Role_ID, first_name, last_name, phone_number)
VALUES
('2', 'Mary', 'Black', '8911-123-45-67'),
('1', 'Ban', 'Stiller', '3459-123-15-73'),
('1', 'Kate', 'Brooks', '8901-759-45-36');
--Insert Category, States
INSERT INTO Category(name) VALUES ('Low'), ('High'), ('Middle');
INSERT INTO States(name) VALUES ('New'), ('Current'), ('Done');
--Insert Items, Comments, Attachments
INSERT INTO Items(User_ID, Category_ID, State_ID, name)
VALUES
('1', '2', '3', 'room'),
('2', '1', '1', 'kitchen'),
('3', '3', '3', 'bathroom');
INSERT INTO Comments(text, Item_ID)
VALUES
('The bed has not yet been brought', '1'),
('Complete kitchen', '2'),
('Order for the bathroom', '3');
INSERT INTO Attachments(file_name, Item_ID)
VALUES
('File_1.txt', '1'),
('File_2.txt', '2'),
('File_3.txt', '3');