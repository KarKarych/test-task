set search_path to market_base;

create table verification_tokens
(
  token   varchar(32) primary key,
  user_id uuid not null,
  constraint fk_verification_tokens_user_id foreign key (user_id) references users (id)
);

insert into verification_tokens
values ('ZDl5rx6Viw0iAHhhMhRrk2TLg8VeQIex', '9da16078-7bfa-4881-8ba4-9eabea16527b');
insert into verification_tokens
values ('rjqIHvefr9CvVhkonaSHzs5rYglUvVZ8', '8c725757-515c-4354-bad1-87bcc2b5a3f8');
insert into verification_tokens
values ('VhkonaSHzs5dir5KUZtHzs5rYglUvVZ8', 'a1927277-5831-402a-9d3c-afa726d75101');