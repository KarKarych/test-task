create schema market_base;

set search_path to market_base;

create table users
(
    id         uuid primary key default gen_random_uuid(),
    secret_key varchar unique not null,
    username   varchar unique not null,
    email      varchar unique not null,
    enabled       bool        not null default false
);

create table user_accounts
(
    user_id  uuid,
    currency varchar(12),
    balance  numeric not null,
    primary key (user_id, currency),
    constraint fk_user_balances_user_id foreign key (user_id) references users (id)
);

create table exchange_rates
(
    from_currency varchar(12),
    to_currency   varchar(12),
    rate          numeric not null,
    primary key (from_currency, to_currency)
);

create table transactions_history
(
    day                date primary key,
    daily_transactions int
);
