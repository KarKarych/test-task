set search_path to market_base;

alter table exchange_rates rename column to_currency to currency_to;
alter table exchange_rates rename column from_currency to currency_from;

drop table transactions_history;
create table transactions_history
(
    id            uuid primary key default gen_random_uuid(),
    user_id       uuid        not null,
    currency_from varchar(12) not null,
    currency_to   varchar(12) not null,
    amount        numeric     not null,
    type          varchar(12) not null,
    created_at    timestamp   not null,
    constraint fk_transactions_history_user_id foreign key (user_id) references users (id)
);