
create type item_count as (name varchar(255), count int);
create table books (name varchar(255), description varchar(255), price int, author varchar(255), publisher varchar(255), isbn varchar(255), year int, primary key (isbn));
create table magazines (name varchar(255), description varchar(255), price int, publisher varchar(255), issn varchar(255), year int, primary key (issn));
create table clients (name varchar(255), age int, email varchar(255) primary key, phoneNumber varchar(255), address varchar(255));
create table librarians (name varchar(255), age int, email varchar(255) primary key, phoneNumber varchar(255), rating int);
create table orders (clientEmail varchar(255), librarianEmail varchar(255), books item_count[], magazines item_count[], primary key (clientEmail, librarianEmail, books, magazines));
create table libraries(name varchar(255), address varchar(255), phoneNumber varchar(255), librarians varchar(255)[], clients varchar(255)[], orders varchar(255), books item_count[], magazines item_count[], primary key (name));