create table telegram_data
(
    id bigserial not null
        constraint telegram_data_pk
            primary key,
    id_user bigint not null
        constraint telegram_data_users_id_fk
            references users (id)
            on update cascade on delete cascade,
    telegram_id int8 not null
);

create unique index telegram_data_id_user_uindex
    on telegram_data (id_user);

create unique index telegram_data_telegram_key_uindex
    on telegram_data (telegram_id);