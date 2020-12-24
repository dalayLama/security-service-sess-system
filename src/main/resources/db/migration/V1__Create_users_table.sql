create table users
(
    id bigserial not null
        constraint users_pk
            primary key,
    email varchar(128) not null,
    security_key varchar(100) not null
);

create unique index users_email_uindex
	on users (email);

create unique index users_security_key_uindex
	on users (security_key);