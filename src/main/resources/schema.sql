CREATE TABLE if not exists person(
                       ID IDENTITY NOT NULL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       photo_url TEXT NULL);