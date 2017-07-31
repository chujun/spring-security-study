create table groups (
	id bigint not null auto_increment primary key,
	group_name varchar(50) not null
);

create table group_authorities (
	group_id bigint not null,
	authority varchar(50) not null
);

create table group_members (
	id bigint not null auto_increment primary key,
	username varchar(50) not null,
	group_id bigint not null
);