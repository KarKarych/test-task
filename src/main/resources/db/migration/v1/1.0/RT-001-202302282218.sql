set search_path to market_base;

alter table users add column authorities varchar(12)[] default '{USER}';