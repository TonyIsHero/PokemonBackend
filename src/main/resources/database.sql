create schema pokemon_db;

create table poke_users(u_id int auto_increment primary key, 
username varchar (200) not null, 
email varchar(30) not null, password varchar (20) not null, 
xP int default 100 not null, status boolean default false not null, score int default 0);