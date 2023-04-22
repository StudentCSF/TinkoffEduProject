--liquibase formatted sql

--changeset valeev:subcr
CREATE SEQUENCE subscription_sequence start with 10 increment by 1;

CREATE TABLE subscription
(
    id      integer not null default nextval('subscription_sequence'),
    link_id integer not null,
    chat_id integer not null,

    CONSTRAINT subscription_pk PRIMARY KEY (id),
    CONSTRAINT subscription_ak UNIQUE (link_id, chat_id)
);

ALTER TABLE subscription
    ADD CONSTRAINT ref_link_id
        FOREIGN KEY (link_id)
            REFERENCES link (id);

ALTER TABLE subscription
    ADD CONSTRAINT ref_chat_id
        FOREIGN KEY (chat_id)
            REFERENCES chat (id);