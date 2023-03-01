set search_path to market_base;

alter table transactions_history alter column created_at type timestamptz;