--liquibase formatted sql

--changeset valeev:chat
CREATE SEQUENCE chat_sequence start with 10 increment by 1;

CREATE TABLE chat
(
    id      integer not null default nextval('chat_sequence'),
    chat_id integer not null,

    CONSTRAINT chat_pk PRIMARY KEY (id),
    CONSTRAINT chat_ak UNIQUE (chat_id)
);