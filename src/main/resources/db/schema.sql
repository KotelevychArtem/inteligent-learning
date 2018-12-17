DROP TABLE IF EXISTS image;
DROP TABLE IF EXISTS theme;
DROP TABLE IF EXISTS lecture;
DROP TABLE IF EXISTS guide;
DROP TABLE IF EXISTS hint;
DROP TABLE IF EXISTS test;

CREATE TABLE image (
	id serial PRIMARY KEY,
	title VARCHAR (32) NOT NULL,
	hash INTEGER NOT NULL,
	bytes bytea NOT NULL,
	UNIQUE (hash, title)
);
CREATE INDEX i_image ON image (hash);

CREATE TABLE theme (
	id serial PRIMARY KEY,
	code VARCHAR (255) NOT NULL,
	name VARCHAR (255),
	description VARCHAR (255)
);

CREATE TABLE lecture (
    themeId INTEGER NOT NULL,
    step INTEGER NOT NULL,
    content TEXT,
    PRIMARY KEY (themeId, step)
);

CREATE TABLE guide (
    themeId INTEGER NOT NULL,
    step INTEGER NOT NULL,
    content TEXT,
    PRIMARY KEY (themeId, step)
);

CREATE TABLE hint (
    themeId INTEGER NOT NULL,
    guideStep INTEGER NOT NULL,
    content TEXT,
    PRIMARY KEY (themeId, guideStep)
);

CREATE TABLE test (
    themeId INTEGER NOT NULL,
    step INTEGER NOT NULL,
    content TEXT,
    PRIMARY KEY (themeId, step)
);