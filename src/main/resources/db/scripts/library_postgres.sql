
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


INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('A4', '210 × 297 mm');
INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('A5', '148 × 210 mm');
INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('A6', '105 × 148 mm');
INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('A7', '74 × 105 mm');
INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('A8', '52 × 74 mm');
INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('A' ,'178 x 111mm');
INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('B (UK)' ,'198 x 129mm');
INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('B (US)' ,'203 x 127mm');
INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('Demy' ,'216 x 138mm');
INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('American Royal' ,'229 x 152mm');
INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('Royal' ,'234 x 156mm');
INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('Pinched Crown Quarto' ,'up to 248 x 171mm');
INSERT INTO library_enum.e_format (s_code, s_dimensions) VALUES ('Crown Quarto' ,'246 x 189mm');


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

INSERT INTO library_enum.e_genre (s_name) VALUES ('mystery');
INSERT INTO library_enum.e_genre (s_name) VALUES ('thriller');
INSERT INTO library_enum.e_genre (s_name) VALUES ('horror');
INSERT INTO library_enum.e_genre (s_name) VALUES ('historical');
INSERT INTO library_enum.e_genre (s_name) VALUES ('romance');
INSERT INTO library_enum.e_genre (s_name) VALUES ('western');
INSERT INTO library_enum.e_genre (s_name) VALUES ('science fiction');
INSERT INTO library_enum.e_genre (s_name) VALUES ('fantasy');
INSERT INTO library_enum.e_genre (s_name) VALUES ('educational');


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
INSERT INTO library.t_author (s_author_name) VALUES ('Karin Slaughter');
INSERT INTO library.t_author (s_author_name) VALUES ('Simon Becket');
INSERT INTO library.t_author (s_author_name) VALUES ('David Baldaci');
INSERT INTO library.t_author (s_author_name) VALUES ('Frank Herbert');
INSERT INTO library.t_author (s_author_name) VALUES ('Stephen King');
INSERT INTO library.t_author (s_author_name) VALUES ('Michael Dibdin');
INSERT INTO library.t_author (s_author_name) VALUES ('Andrzej Sapkowski');
INSERT INTO library.t_author (s_author_name) VALUES ('Andy Weir');
INSERT INTO library.t_author (s_author_name) VALUES ('Tom Rob Smith');
INSERT INTO library.t_author (s_author_name) VALUES ('Jo Nesbo');


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

INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) 	VALUES ('9780552159708', 'Angels & Demons', 	624, 2, 11, 'Transworld Publishers Limited', 1, 1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) 	VALUES ('9780552159715', 'The Da Vinci Code',	864, 2, 10, 'Transworld Publishers Limited', 1, 2, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) 	VALUES ('9780552149525', 'The Lost Symbol', 	672, 2, 10, 'Transworld Publishers Limited', 1, 3, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) 	VALUES ('9780593072493', 'Inferno', 			480, 2, 10, 'Transworld Publishers Limited', 1, 4, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id)	VALUES ('9780552174169', 'Origin', 				480, 2, 10, 'Transworld Publishers Limited', 1, 5, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_genre_id) 					VALUES ('9780552161251', 'Digital Fortress', 	512, 2, 10, 'Transworld Publishers Limited', 1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_genre_id) 					VALUES ('9780552159722', 'Deception Point', 	580, 2, 10, 'Transworld Publishers Limited', 1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_genre_id)                   VALUES ('9781784703820', 'Blood On Snow',       172, 2, 10, 'VINTAGE', 1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_genre_id)                   VALUES ('9780099551744', 'The Snowman',         550, 2, 10, 'VINTAGE', 1, 3);


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

INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (1, 1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (2, 1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (3, 1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (4, 1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (5, 1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (6, 1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (7, 1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (8, 14, true);


---------------------------------------
select * from library_enum.e_binding_type;
select * from library_enum.e_format;
select * from library_enum.e_language;

select * from library.t_author;
select * from library.t_book;
select * from library.t_book_author;
