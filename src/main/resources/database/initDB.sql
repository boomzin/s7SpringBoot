DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS friendship;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TYPE status AS ENUM ('OPEN', 'TAKEN');
CREATE TYPE gender AS ENUM ('MALE', 'FEMALE');

CREATE TABLE users
(
    id        INTEGER DEFAULT NEXT VALUE FOR global_seq,
    name      VARCHAR                NOT NULL,
    email     VARCHAR                NOT NULL,
    password  VARCHAR                NOT NULL,
    age       INTEGER                NOT NULL,
    has_photo BOOLEAN DEFAULT FALSE  NOT NULL,
    gender    gender  DEFAULT 'MALE' NOT NULL,
    status    status  DEFAULT 'OPEN' NOT NULL,
    CONSTRAINT USER_PRIMARY_KEY PRIMARY KEY (id)
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE friendship
(
    user_id   INTEGER NOT NULL,
    friend_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, friend_id),
    CONSTRAINT USER_FOREIGN_KEY FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT FRIEND_FOREIGN_KEY FOREIGN KEY (friend_id) REFERENCES users (id)
);

