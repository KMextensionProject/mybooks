
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
    s_code character varying(10) COLLATE pg_catalog."default" NOT NULL,
    s_dimensions character varying(20) COLLATE pg_catalog."default" NOT NULL,
    d_from timestamp without time zone NOT NULL DEFAULT now(),
    d_to timestamp without time zone NOT NULL DEFAULT 'infinity'::timestamp without time zone,
    CONSTRAINT e_format_pkey PRIMARY KEY (n_format_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS library_enum.e_format
    OWNER to postgres;

INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('A4', '210 × 297 mm');
INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('A5', '148 × 210 mm');
INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('A6', '105 × 148 mm');
INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('A7', '74 × 105 mm');
INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('A8', '52 × 74 mm');


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

INSERT INTO library_enum.e_language (s_code, s_name) VALUES ('EN', 'English');
INSERT INTO library_enum.e_language (s_code, s_name) VALUES ('SK', 'Slovenský');
INSERT INTO library_enum.e_language (s_code, s_name) VALUES ('CZ', 'Český');


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

INSERT INTO library.t_author (s_author_name) VALUES ('Dan Brown');
INSERT INTO library.t_author (s_author_name) VALUES ('Lee Child');
INSERT INTO library.t_author (s_author_name) VALUES ('George R.R. Martin');
INSERT INTO library.t_author (s_author_name) VALUES ('J.K. Rowling');


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
	n_series_number integer NOT NULL DEFAULT 0,
    d_from timestamp without time zone NOT NULL DEFAULT now(),
    d_to timestamp without time zone NOT NULL DEFAULT 'infinity'::timestamp without time zone,
    CONSTRAINT t_book_pkey PRIMARY KEY (n_book_id),
    CONSTRAINT n_binding_fk FOREIGN KEY (n_binding_type_id)
        REFERENCES library_enum.e_binding_type (n_binding_type_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET NULL
        NOT VALID,
    CONSTRAINT n_format_fk FOREIGN KEY (n_format_id)
        REFERENCES library_enum.e_format (n_format_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET NULL
        NOT VALID,
    CONSTRAINT n_language_fk FOREIGN KEY (n_language_id)
        REFERENCES library_enum.e_language (n_language_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET NULL
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS library.t_book
    OWNER to postgres;

-- TODO: add year published?
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number) 	VALUES ('9780552159708', 'Angels & Demons', 	624, 2, 5, 'Transworld Publishers Limited', 1, 1);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number) 	VALUES ('9780552159715', 'The Da Vinci Code',	864, 2, 2, 'Transworld Publishers Limited', 1, 2);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number) 	VALUES ('9780552149525', 'The Lost Symbol', 	672, 2, 2, 'Transworld Publishers Limited', 1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number) 	VALUES ('9780593072493', 'Inferno', 			480, 1, 2, 'Transworld Publishers Limited', 1, 4);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number)	VALUES ('9780552174169', 'Origin', 				480, 2, 2, 'Transworld Publishers Limited', 1, 5);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id) 					VALUES ('9780552161251', 'Digital Fortress', 	512, 2, 2, 'Transworld Publishers Limited', 1);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id) 					VALUES ('9780552159722', 'Deception Point', 	580, 2, 2, 'Transworld Publishers Limited', 1);


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

INSERT INTO library.t_book_author (n_book_id, n_author_id) VALUES (1, 1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id) VALUES (2, 1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id) VALUES (3, 1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id) VALUES (4, 1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id) VALUES (5, 1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id) VALUES (6, 1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id) VALUES (7, 1, true);


---------------------------------------
select * from library_enum.e_binding_type;
select * from library_enum.e_format;
select * from library_enum.e_language;

select * from library.t_author;
select * from library.t_book;
select * from library.t_book_author;
