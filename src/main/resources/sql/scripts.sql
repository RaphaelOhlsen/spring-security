create database db_bank_backend;

user db_bank_backend;

create table users(
	username varchar(50) COLLATE utf8mb4_general_ci not null primary key,
	password VARCHAR(500) COLLATE utf8mb4_general_ci not null,
	enabled boolean not null
);

create table authorities (
	username varchar(50) COLLATE utf8mb4_general_ci not null,
	authority VARCHAR(500) COLLATE utf8mb4_general_ci not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

INSERT INTO users (username, password, enabled) VALUES ('raphael', '12345', true);
INSERT INTO authorities (username, authority) VALUES ('raphael', 'write');