DROP TABLE IF EXISTS department;

DROP INDEX IF EXISTS i_subject;
DROP TABLE IF EXISTS subject;

DROP INDEX IF EXISTS i_theme;
DROP TABLE IF EXISTS theme;

DROP INDEX IF EXISTS i_test;
DROP TABLE IF EXISTS test;

DROP INDEX IF EXISTS i_question;
DROP TABLE IF EXISTS question;

DROP INDEX IF EXISTS i_lecture;
DROP TABLE IF EXISTS lecture;


CREATE TABLE department (
	id serial PRIMARY KEY,
	name VARCHAR (255)
);

CREATE TABLE subject (
    id serial PRIMARY KEY,
    departmentId INTEGER NOT NULL,
    name VARCHAR (255)
);
CREATE INDEX i_subject ON subject (departmentId);

CREATE TABLE theme (
	id serial PRIMARY KEY,
	subjectId INTEGER NOT NULL,
	name VARCHAR (255),
	description VARCHAR (255)
);
CREATE INDEX i_theme ON theme (subjectId);

CREATE TABLE test (
	id serial PRIMARY KEY,
	themeId INTEGER NOT NULL,
	name VARCHAR (255)
);
CREATE INDEX i_test ON test (themeId);

CREATE TABLE question (
	id serial PRIMARY KEY,
	testId INTEGER NOT NULL,
	position INTEGER NOT NULL,
	name VARCHAR (255) NOT NULL,
	condition TEXT NOT NULL,
	rightAnswer TEXT NOT NULL
);
CREATE INDEX i_question ON question (testId);

CREATE TABLE lecture (
    id serial PRIMARY KEY,
    themeId INTEGER NOT NULL,
    name VARCHAR (255) NOT NULL,
    content TEXT
);
CREATE INDEX i_lecture ON test (themeId);