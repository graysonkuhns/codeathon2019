# Refresh the database
DROP DATABASE IF EXISTS devet;
CREATE DATABASE devet;
USE devet;

# Create the service account and grant it admin permissions on the database
CREATE OR REPLACE USER `devet`@`%` IDENTIFIED BY 'devet';
GRANT ALL PRIVILEGES ON devet.* TO `devet`@`%` WITH GRANT OPTION;

# Create database schema
CREATE TABLE repository (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    owner VARCHAR(30) NOT NULL,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE language (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(30)
);

CREATE TABLE file_extension (
	extension VARCHAR(10) PRIMARY KEY,
	language INTEGER REFERENCES language(id)
);

CREATE TABLE job (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(30)
);

# Populate programming languages
INSERT INTO language (name) VALUES ('Java');
INSERT INTO language (name) VALUES ('HTML');
INSERT INTO language (name) VALUES ('CSS');
INSERT INTO language (name) VALUES ('Ruby');
INSERT INTO language (name) VALUES ('Go');
INSERT INTO language (name) VALUES ('TypeScript');
INSERT INTO language (name) VALUES ('JavaScript');                                                                                                                                           ;
INSERT INTO language (name) VALUES ('Python');
INSERT INTO language (name) VALUES ('C');
INSERT INTO language (name) VALUES ('C++');
INSERT INTO language (name) VALUES ('C#');
INSERT INTO language (name) VALUES ('Shell');
INSERT INTO language (name) VALUES ('PHP');

# Populate file extensions
INSERT INTO file_extension (language, extension) VALUES (1, 'java');
INSERT INTO file_extension (language, extension) VALUES (2, 'html');
INSERT INTO file_extension (language, extension) VALUES (2, 'htm');
INSERT INTO file_extension (language, extension) VALUES (3, 'css');
INSERT INTO file_extension (language, extension) VALUES (4, 'rb');
INSERT INTO file_extension (language, extension) VALUES (5, 'go');
INSERT INTO file_extension (language, extension) VALUES (6, 'ts');
INSERT INTO file_extension (language, extension) VALUES (6, 'tsx');
INSERT INTO file_extension (language, extension) VALUES (7, 'js');
INSERT INTO file_extension (language, extension) VALUES (7, 'jsx');
INSERT INTO file_extension (language, extension) VALUES (8, 'py');
INSERT INTO file_extension (language, extension) VALUES (9, 'c');
INSERT INTO file_extension (language, extension) VALUES (10, 'cpp');
INSERT INTO file_extension (language, extension) VALUES (11, 'cs');
INSERT INTO file_extension (language, extension) VALUES (12, 'sh');
INSERT INTO file_extension (language, extension) VALUES (13, 'php');

CREATE TABLE repository_language (
	repository INTEGER REFERENCES repository(id),
	language INTEGER REFERENCES language(id),
	byte_count BIGINT
);

CREATE TABLE commit (
	hash VARCHAR(30) PRIMARY KEY,
	author VARCHAR(30),
	timestamp TIMESTAMP
);

CREATE TABLE commit_language (
	commit VARCHAR(30) PRIMARY KEY REFERENCES commit(hash),
	language INTEGER REFERENCES language(id),
	file_count INTEGER
);
