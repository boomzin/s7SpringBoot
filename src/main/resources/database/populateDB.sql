INSERT INTO users (EMAIL, PASSWORD, NAME, AGE, HAS_PHOTO, GENDER, STATUS)
VALUES ('user1@gmail.com', '{noop}user1pass', 'user1', 16, TRUE, 'MALE', 'OPEN'),
       ('user2@gmail.com', '{noop}user2pass', 'user2', 19, TRUE, 'FEMALE', 'TAKEN'),
       ('user3@gmail.com', '{noop}user3pass', 'user3', 36, FALSE, 'MALE', 'TAKEN'),
       ('user4@gmail.com', '{noop}user4pass', 'user4', 30, TRUE, 'FEMALE', 'OPEN'),
       ('user5@gmail.com', '{noop}user5pass', 'user5', 14, FALSE, 'MALE', 'OPEN'),
       ('user6@gmail.com', '{noop}user6pass', 'user6', 28, FALSE, 'FEMALE', 'OPEN'),
       ('user7@gmail.com', '{noop}user7pass', 'user7', 46, TRUE, 'MALE', 'TAKEN');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('USER', 2),
       ('USER', 3),
       ('USER', 4),
       ('USER', 5),
       ('USER', 6),
       ('USER', 7);

INSERT INTO FRIENDSHIP (user_id, friend_id)
VALUES (1, 2),
       (1, 3),
       (1, 7),
       (2, 7),
       (2, 6),
       (6, 7),
       (6, 5);


