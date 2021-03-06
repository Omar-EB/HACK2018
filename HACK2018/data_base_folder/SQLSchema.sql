-- DB SCHEMA
CREATE TABLE uTable (
	--UID SERIAL PRIMARY KEY,
	NAME VARCHAR(20) PRIMARY KEY
);

CREATE TABLE links(
	URL VARCHAR(2000) NOT NULL,
	UID VARCHAR(20) REFERENCES uTable(name) NOT NULL
);

CREATE TABLE shared(
	source VARCHAR(20) REFERENCES uTable(name) NOT NULL,
	destination VARCHAR(20) REFERENCES uTable(name) NOT NULL,
	URL VARCHAR(2000) NOT NULL
)


-- QUERIES

-- NEW USER
INSERT INTO utable(name) VALUES ('name');

-- NEW LINK
-- Replace 2nd UID with actual UID
SELECT UID FROM uTable WHERE (name='name');
INSERT INTO links(URL, UID) VALUES ('URL', UID);

-- FIND LINK
-- Replace 2nd UID with actual UID
SELECT URL FROM links WHERE (UID=UID);

-- DELETE SAVED LINK
DELETE FROM links WHERE (UID=UID AND URL=URL);