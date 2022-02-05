INSERT INTO users (EMAIL, PASSWORD, NAME, AGE, HAS_PHOTO, GENDER, STATUS)
VALUES ('user1@gmail.com', 'user1pass', '$2a$12$rV.yyV7xYHthUCEUTb5hG.aRCy4FF5T9MkrruBLHH8RLM6IMh2Lui', 16, TRUE, 'MALE', 'OPEN'),
       ('user2@gmail.com', 'user2pass', 'user2', 19, TRUE, 'FEMALE', 'TAKEN'),
       ('user3@gmail.com', 'user3pass', 'user3', 36, FALSE, 'MALE', 'TAKEN'),
       ('user4@gmail.com', 'user4pass', 'user4', 30, TRUE, 'FEMALE', 'OPEN'),
       ('user5@gmail.com', 'user5pass', 'user5', 14, FALSE, 'MALE', 'OPEN'),
       ('user6@gmail.com', 'user6pass', 'user6', 28, FALSE, 'FEMALE', 'OPEN'),
       ('user7@gmail.com', 'user7pass', 'user7', 46, TRUE, 'MALE', 'TAKEN');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('USER', 100002),
       ('USER', 100003),
       ('USER', 100004),
       ('USER', 100005),
       ('USER', 100006);

INSERT INTO FRIENDSHIP (user_id, friend_id)
VALUES (100000, 100001),
       (100000, 100002),
       (100000, 100006),
       (100001, 100006),
       (100001, 100005),
       (100005, 100006),
       (100005, 100004);


