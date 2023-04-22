--liquibase formatted sql

--changeset valeev:link
CREATE SEQUENCE link_sequence start with 10 increment by 1;

CREATE TABLE link
(
    id         integer      not null default nextval('link_sequence'),
    url        varchar(255) not null,
    updated_at date         not null,

    CONSTRAINT link_pk PRIMARY KEY (id)
);