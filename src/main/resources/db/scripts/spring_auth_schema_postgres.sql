-- SCHEMA: auth
-- DROP SCHEMA IF EXISTS auth ;
CREATE SCHEMA IF NOT EXISTS auth
    AUTHORIZATION postgres;

-- Table: auth.users
-- DROP TABLE IF EXISTS auth.users;
CREATE TABLE IF NOT EXISTS auth.users
(
    username character varying(50) COLLATE pg_catalog."default" NOT NULL,
    password character varying(200) COLLATE pg_catalog."default" NOT NULL,
    enabled boolean NOT NULL DEFAULT false,
    CONSTRAINT users_pkey PRIMARY KEY (username)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS auth.users
    OWNER to postgres;

-- Table: auth.authorities
-- DROP TABLE IF EXISTS auth.authorities;
CREATE TABLE IF NOT EXISTS auth.authorities
(
    username character varying(50) COLLATE pg_catalog."default" NOT NULL,
    authority character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT ix_auth_username UNIQUE (username, authority),
    CONSTRAINT fk_authorities_users FOREIGN KEY (username)
        REFERENCES auth.users (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS auth.authorities
    OWNER to postgres;

-- INSERTS --

INSERT INTO auth.authorities (username, authority) VALUES ('admin', 'USER');
INSERT INTO auth.authorities (username, authority) VALUES ('admin', 'ADMIN');
INSERT INTO auth.authorities (username, authority) VALUES ('supervisor', 'USER');

INSERT INTO auth.users (username, password, enabled) 
    VALUES ('admin', '$2a$12$PbkbTH1Dkqp0jA6rfWQAouoGW85DK4sdyr0ESwQA/tvvb/HQHpypm', true);
INSERT INTO auth.users (username, password, enabled) 
    VALUES ('supervisor', '$2a$12$P5NFWnztcO8mF1ZsCQIwru34Kw3HEGUkBnvlAWU/wtWPj9BuLqQhK', true);
