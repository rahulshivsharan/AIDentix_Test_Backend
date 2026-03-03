create table userstbl (
	id serial primary key,
	username varchar(100) not null unique,
	password varchar(255) not null
);


select * from userstbl;

insert into userstbl(username, password) values('Quintine Torantino', 'qwerty@123');
insert into userstbl(username, password) values('Samuel Jackson', 'sam@123');
insert into userstbl(username, password) values('Jim Nadkarni', 'jim@123');
insert into userstbl(username, password) values('Abraham Waghela', 'wagh@123');
insert into userstbl(username, password) values('Ricardo Gaitonde', 'ric@123');
insert into userstbl(username, password) values('Pandurang Lincoln', 'pan@123');