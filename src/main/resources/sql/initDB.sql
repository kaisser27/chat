CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    login    VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS chats
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)

);

CREATE TABLE IF NOT EXISTS chat_user
(
    chat_id BIGINT,
    user_id BIGINT,
    name    VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS messages
(
    id             BIGSERIAL PRIMARY KEY,
    name_of_sender VARCHAR(255) NOT NULL,
    text           VARCHAR(255) NOT NULL,
    chat           BIGINT references chats (id),
    sending_time   date
);