-- Database: fs_scanner

-- DROP DATABASE IF EXISTS fs_scanner;

CREATE DATABASE fs_scanner
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1251'
    LC_CTYPE = 'English_United States.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- SCHEMA: fs_scanner

-- DROP SCHEMA IF EXISTS fs_scanner ;

CREATE SCHEMA IF NOT EXISTS fs_scanner
    AUTHORIZATION postgres;

-- Table: fs_scanner.files

-- DROP TABLE IF EXISTS fs_scanner.files;

CREATE TABLE IF NOT EXISTS fs_scanner.files
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( CYCLE INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    file_path text COLLATE pg_catalog."default" NOT NULL,
    is_directory boolean NOT NULL,
    CONSTRAINT files_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS fs_scanner.files
    OWNER to postgres;