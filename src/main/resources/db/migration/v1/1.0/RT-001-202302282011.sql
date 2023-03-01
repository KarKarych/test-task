set search_path to market_base;

insert into market_base.users (id, secret_key, username, email, enabled) values ('ea8b3eab-21eb-4f55-92b8-b16b5e01bbcd', 'c', 'c', 'c', true);
insert into market_base.users (id, secret_key, username, email) values ('a1927277-5831-402a-9d3c-afa726d75101', 'd', 'd', 'd');

insert into market_base.user_accounts (user_id, currency, balance) values ('a1927277-5831-402a-9d3c-afa726d75101', 'RUB', 123123.3);
insert into market_base.user_accounts (user_id, currency, balance) values ('ea8b3eab-21eb-4f55-92b8-b16b5e01bbcd', 'TON', 12312.12);
insert into market_base.user_accounts (user_id, currency, balance) values ('a1927277-5831-402a-9d3c-afa726d75101', 'TON', 231);
insert into market_base.user_accounts (user_id, currency, balance) values ('a1927277-5831-402a-9d3c-afa726d75101', 'BTC', 1223);
insert into market_base.user_accounts (user_id, currency, balance) values ('ea8b3eab-21eb-4f55-92b8-b16b5e01bbcd', 'RUB', 162810);
insert into market_base.user_accounts (user_id, currency, balance) values ('ea8b3eab-21eb-4f55-92b8-b16b5e01bbcd', 'BTC', 2323);