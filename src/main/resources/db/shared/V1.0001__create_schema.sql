CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create TABLE "users"
(
    id                 uuid PRIMARY KEY                  DEFAULT uuid_generate_v4(),
    version            bigint                   not null default 0,
    username           text                     NOT NULL,
    date_of_birth	   date NOT NULL,
    created_at         timestamp with time zone NOT NULL default current_timestamp,
    modified_at        timestamp with time zone NOT NULL default current_timestamp
);