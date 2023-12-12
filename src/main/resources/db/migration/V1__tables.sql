CREATE TABLE users(
     id         SERIAL PRIMARY KEY,
     email   VARCHAR(255) NOT NULL,
     password   VARCHAR(255) NOT NULL,
     company_name VARCHAR(255) NOT NULL,
     roles   VARCHAR(255) NOT NULL,
     created_at TIMESTAMP(6) NOT NULL,
     UNIQUE(email)
);

CREATE TABLE chat_room(
     id         SERIAL PRIMARY KEY,
     name       VARCHAR(255) NOT NULL,
     created_at TIMESTAMP(6) NOT NULL
);

CREATE TABLE user_chatroom(
     user_id     SERIAL REFERENCES users(id),
     chatroom_id SERIAL REFERENCES chat_room(id),
     PRIMARY KEY (user_id, chatroom_id)
);

CREATE TABLE message(
     id           SERIAL PRIMARY KEY,
     content      TEXT,
     user_id      SERIAL,
     chat_room_id SERIAL,
     created_at   TIMESTAMP(6) NOT NULL,
     FOREIGN KEY (user_id) REFERENCES users(id),
     FOREIGN KEY (chat_room_id) REFERENCES chat_room(id)
);