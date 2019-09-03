--Create table
create table vacancies_sql_ru (
    id serial primary key not null,
    name varchar(200) not null unique,
    text text,
    link varchar(2000) not null unique
);