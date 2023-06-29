
/***************************************************************/
--							E N U M S
/***************************************************************/

-- Table: library_enum.e_binding_type
-- DROP TABLE IF EXISTS library_enum.e_binding_type;
CREATE TABLE IF NOT EXISTS library_enum.e_binding_type
(
    n_binding_type_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    s_name character varying COLLATE pg_catalog."default" NOT NULL,
    d_from timestamp without time zone NOT NULL DEFAULT now(),
    d_to timestamp without time zone NOT NULL DEFAULT 'infinity'::timestamp without time zone,
    CONSTRAINT e_binding_type_pkey PRIMARY KEY (n_binding_type_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS library_enum.e_binding_type
    OWNER to postgres;

INSERT INTO library_enum.e_binding_type (s_name) VALUES ('Hard cover');
INSERT INTO library_enum.e_binding_type (s_name) VALUES ('Paperback');
INSERT INTO library_enum.e_binding_type (s_name) VALUES ('Folding-picture');


-- Table: library_enum.e_format
-- DROP TABLE IF EXISTS library_enum.e_format;
CREATE TABLE IF NOT EXISTS library_enum.e_format
(
    n_format_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    s_code character varying(30) COLLATE pg_catalog."default" NOT NULL,
    s_dimensions character varying(20) COLLATE pg_catalog."default" NOT NULL,
    d_from timestamp without time zone NOT NULL DEFAULT now(),
    d_to timestamp without time zone NOT NULL DEFAULT 'infinity'::timestamp without time zone,
    CONSTRAINT e_format_pkey PRIMARY KEY (n_format_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS library_enum.e_format
    OWNER to postgres;


-- Table: library_enum.e_genre
-- DROP TABLE IF EXISTS library_enum.e_genre;
CREATE TABLE IF NOT EXISTS library_enum.e_genre
(
    n_genre_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    s_name character varying(25) COLLATE pg_catalog."default" NOT NULL,
    d_from timestamp without time zone NOT NULL DEFAULT now(),
    d_to timestamp without time zone NOT NULL DEFAULT 'infinity'::timestamp without time zone,
    CONSTRAINT e_genre_pkey PRIMARY KEY (n_genre_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS library_enum.e_genre
    OWNER to postgres;


-- Table: library_enum.e_language
-- DROP TABLE IF EXISTS library_enum.e_language;
CREATE TABLE IF NOT EXISTS library_enum.e_language
(
    n_language_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    s_code character varying(3) COLLATE pg_catalog."default" NOT NULL,
    s_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    d_from timestamp without time zone NOT NULL DEFAULT now(),
    d_to timestamp without time zone NOT NULL DEFAULT 'infinity'::timestamp without time zone,
    CONSTRAINT e_language_pkey PRIMARY KEY (n_language_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS library_enum.e_language
    OWNER to postgres;


/***************************************************************/
--							T A B L E S
/***************************************************************/

-- Table: library.t_author
-- DROP TABLE IF EXISTS library.t_author;
CREATE TABLE IF NOT EXISTS library.t_author
(
    n_author_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    s_author_name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT t_author_pkey PRIMARY KEY (n_author_id),
    CONSTRAINT s_name_unq UNIQUE (s_author_name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS library.t_author
    OWNER to postgres;


-- Table: library.t_book
-- DROP TABLE IF EXISTS library.t_book;
CREATE TABLE IF NOT EXISTS library.t_book
(
    n_book_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    s_isbn character varying(13) COLLATE pg_catalog."default" NOT NULL,
    s_title character varying(50) COLLATE pg_catalog."default" NOT NULL,
    n_pages integer NOT NULL,
    n_binding_type_id integer NOT NULL,
    n_format_id integer NOT NULL,
    s_publisher character varying(100) COLLATE pg_catalog."default",
    n_language_id integer NOT NULL,
    n_series_number integer,
    d_from timestamp without time zone NOT NULL DEFAULT now(),
    d_to timestamp without time zone NOT NULL DEFAULT 'infinity'::timestamp without time zone,
    n_genre_id integer NOT NULL DEFAULT 10,
    CONSTRAINT t_book_pkey PRIMARY KEY (n_book_id),
    CONSTRAINT n_binding_fk FOREIGN KEY (n_binding_type_id)
        REFERENCES library_enum.e_binding_type (n_binding_type_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET NULL,
    CONSTRAINT n_format_fk FOREIGN KEY (n_format_id)
        REFERENCES library_enum.e_format (n_format_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET NULL,
    CONSTRAINT n_genre_fk FOREIGN KEY (n_genre_id)
        REFERENCES library_enum.e_genre (n_genre_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT n_language_fk FOREIGN KEY (n_language_id)
        REFERENCES library_enum.e_language (n_language_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET NULL
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS library.t_book
    OWNER to postgres;


-- Table: library.t_book_author
-- DROP TABLE IF EXISTS library.t_book_author;
CREATE TABLE IF NOT EXISTS library.t_book_author
(
    n_book_id integer NOT NULL,
    n_author_id integer NOT NULL,
    b_lead_author boolean NOT NULL DEFAULT false,
    CONSTRAINT book_author_unq UNIQUE (n_book_id, n_author_id),
    CONSTRAINT n_author_id_fk FOREIGN KEY (n_author_id)
        REFERENCES library.t_author (n_author_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT n_book_id_fk FOREIGN KEY (n_book_id)
        REFERENCES library.t_book (n_book_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS library.t_book_author
    OWNER to postgres;

