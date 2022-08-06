-- some test users

INSERT INTO user_roles (id, user_role)
values
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO users (id, email, first_name, last_name, image_url, is_active, password)
VALUES
    (1, 'admin@example.com', 'Admin', 'Adminov', null, 1, 'asdasdasd'),
    (2, 'user@example.com', 'User', 'Userov', null, 1, 'asdasdasd');


INSERT INTO users_user_roles (user_entity_id, user_roles_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 2);