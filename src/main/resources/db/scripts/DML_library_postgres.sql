
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


INSERT INTO library_enum.e_genre (s_name) VALUES ('mystery');
INSERT INTO library_enum.e_genre (s_name) VALUES ('thriller');
INSERT INTO library_enum.e_genre (s_name) VALUES ('horror');
INSERT INTO library_enum.e_genre (s_name) VALUES ('historical');
INSERT INTO library_enum.e_genre (s_name) VALUES ('romance');
INSERT INTO library_enum.e_genre (s_name) VALUES ('western');
INSERT INTO library_enum.e_genre (s_name) VALUES ('science fiction');
INSERT INTO library_enum.e_genre (s_name) VALUES ('fantasy');
INSERT INTO library_enum.e_genre (s_name) VALUES ('educational');
INSERT INTO library_enum.e_genre (s_name) VALUES ('drama');


INSERT INTO library_enum.e_language (s_code, s_name) VALUES ('EN', 'English');
INSERT INTO library_enum.e_language (s_code, s_name) VALUES ('SK', 'Slovenský');
INSERT INTO library_enum.e_language (s_code, s_name) VALUES ('CZ', 'Český');


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


INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9780552159708', 'Angels & Demons', 	        620, 2, 11, 'Transworld Publishers Limited', 1, 1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9780552159715', 'The Da Vinci Code',        590, 2, 10, 'Transworld Publishers Limited', 1, 2, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9780552149525', 'The Lost Symbol', 	        670, 2, 10, 'Transworld Publishers Limited', 1, 3, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9780593072493', 'Inferno', 			        620, 2, 10, 'Transworld Publishers Limited', 1, 4, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9780552174169', 'Origin', 			        542, 2, 10, 'Transworld Publishers Limited', 1, 5, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_genre_id) 				   VALUES ('9780552161251', 'Digital Fortress',         510, 2, 10, 'Transworld Publishers Limited', 1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_genre_id) 				   VALUES ('9780552159722', 'Deception Point', 	        585, 2, 10, 'Transworld Publishers Limited', 1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_genre_id)                  VALUES ('9781784703820', 'Blood On Snow',            172, 2, 10, 'VINTAGE', 1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_genre_id)                  VALUES ('9780099551744', 'The Snowman',              550, 2, 10, 'VINTAGE', 1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9780006479888', 'A Game Of Thrones',        801, 2, 10, 'Harper Voyager', 1, 1, 9);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9780006479895', 'A Clash Of Kings',         913, 2, 10, 'Harper Voyager', 1, 2, 9);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9780006479901', 'A Storm Of Swords 1',      623, 2, 10, 'Harper Voyager', 1, 3, 9);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9780007119554', 'A Storm Of Swords 2',      607, 2, 10, 'Harper Voyager', 1, 4, 9);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9780006486121', 'A Feast For Crows',        849, 2, 10, 'Harper Voyager', 1, 5, 9);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9780006486114', 'A Dance With Dragons',     1181,2, 10, 'Harper Voyager', 1, 6, 9);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_genre_id)                  VALUES ('9780553824797', 'Whispers Of The Dead',     477, 2, 10, 'Bantam Books', 1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_genre_id)                  VALUES ('9780553817492', 'The Chemistry Of Dead',    444, 2, 10, 'Bantam Books', 1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_genre_id)                  VALUES ('9780553817508', 'Written In Bone',          492, 2, 10, 'Bantam Books', 1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_genre_id)                  VALUES ('9780553825596', 'The Calling Of The Grave', 457, 2, 10, 'Bantam Books', 1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_genre_id)                  VALUES ('9781471137853', 'Child 44',                 478, 2, 10, 'Simon and Schuster', 1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_genre_id)                  VALUES ('9780330518048', 'True Blue',                444, 2, 10, 'PAN',   1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_genre_id)                  VALUES ('9781101905555', 'The Martian',              492, 2, 10, 'Crown', 1, 8);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_genre_id)                  VALUES ('9780571154210', 'Ratking',                  457, 2, 10, 'Faber and Faber',    1, 3);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9781444723441', 'The Gunslinger',           238, 2, 11, 'HODDER', 1, 1, 9);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9781444723458', 'The Drawing Of The Three', 455, 2, 11, 'HODDER', 1, 2, 9);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9781444723465', 'The Waste Lands',          584, 2, 11, 'HODDER', 1, 3, 9);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9781444723472', 'Wizadr And Glass',         845, 2, 11, 'HODDER', 1, 4, 9);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9781444723489', 'Wolves Of The Calla',      770, 2, 11, 'HODDER', 1, 5, 9);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9781444723496', 'Song Of Susannah',         436, 2, 11, 'HODDER', 1, 6, 9);
INSERT INTO library.t_book (s_isbn, s_title, n_pages, n_binding_type_id, n_format_id, s_publisher, n_language_id, n_series_number, n_genre_id) VALUES ('9781444723502', 'The Dark Tower',           686, 2, 11, 'HODDER', 1, 7, 9);


INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (1,   1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (2,   1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (3,   1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (4,   1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (5,   1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (6,   1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (7,   1, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (8,  14, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (9,  14, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (10,  3, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (11,  3, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (12,  3, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (13,  3, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (14,  3, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (15,  3, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (16, 11, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (17, 11, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (18, 11, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (19, 11, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (20, 18, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (21, 12, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (22, 17, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (23, 15, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (24,  9, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (25,  9, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (26,  9, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (27,  9, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (28,  9, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (29,  9, true);
INSERT INTO library.t_book_author (n_book_id, n_author_id, b_lead_author) VALUES (30,  9, true);

---------------------------------------
select * from library_enum.e_binding_type;
select * from library_enum.e_format;
select * from library_enum.e_language;
select * from library_enum.e_genre;

select * from library.t_author;
select * from library.t_book;
select * from library.t_book_author;
