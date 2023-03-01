set search_path to market_base;

insert into users (secret_key, username, email, authorities, enabled)
values ('rjqIHvefr9CvVhkonaSHzs5rYglUvVZ8i7I', 'adminHuman', 'adminHuman@gmail.com', '{ADMIN, USER}', true);
insert into users (secret_key, username, email, authorities, enabled)
values ('bpeBIoOfKAAdir5KUZtF4fmF57mwZmpy4sF', 'adminNotAHuman', 'adminNotAHuman@gmail.com', '{ADMIN}', true);
insert into users (id, secret_key, username, email, authorities)
values ('9da16078-7bfa-4881-8ba4-9eabea16527b', 'ZDl5rx6Viw0iAHhhMhRrk2TLg8VeQIexHY8', 'adminHumanInactive', 'adminHumanInactive@gmail.com', '{ADMIN, USER}');
insert into users (id, secret_key, username, email, authorities)
values ('8c725757-515c-4354-bad1-87bcc2b5a3f8', 'VhkonaSHzs5dir5KUZtHzs5rYglUvVZ8i7I', 'adminNotAHumanInactive', 'adminNotAHumanInactive@gmail.com', '{ADMIN}');
