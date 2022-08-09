-- some user roles

INSERT INTO user_roles (id, user_role)
values (1, 'ADMIN'),
       (2, 'USER');

-- some test users

INSERT INTO users (id, email, first_name, last_name, is_active, password)
VALUES (1, 'admin@example.com', 'Admin', 'Adminov', 1, '0f7e9ff03817225550b0d3ed4dacfadaffb4e6064c64f8baa73eb53ebaad15f8c1791f71766ba8d0'),
       (2, 'user@example.com', 'User', 'Userov', 1, '0f7e9ff03817225550b0d3ed4dacfadaffb4e6064c64f8baa73eb53ebaad15f8c1791f71766ba8d0'),
       (3, 'test@test', 'test', 'testov', 1, '0f7e9ff03817225550b0d3ed4dacfadaffb4e6064c64f8baa73eb53ebaad15f8c1791f71766ba8d0');




-- some roles to the test users
INSERT INTO users_user_roles (user_entity_id, user_roles_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (3, 1),
       (3, 2);

-- some pets
INSERT INTO pets (id, age , breed, comment, name, picture_url, species, owner_id, price)
VALUES
    (1, 1 , 'Mandragorka' , 'Best and nicest kitty there is. NOW ON PROMOTION', 'Betty',
     'https://upload.wikimedia.org/wikipedia/commons/3/3a/Russian_blue.jpg',
     'CAT', 2, 100),
    (2, 12 , 'Lubov' , 'No breed as it was picked up from the street to make some cash on this site!', 'Bally',
     'https://miau.bg/files/lib/600x350/munchkin-cat.jpg',
     'CAT', 3, 200),
    (3, 2 , 'Golden Retriever' , 'Sweet and cuddly! Many hair though...', 'Johnny',
     'https://upload.wikimedia.org/wikipedia/commons/8/82/Golden_Retriever_standing_Tucker.jpg',
     'DOG', 2, 350),
    (4, 1 , 'HotDog' , 'It can always end up in the dog.. :)', 'Sausagy',
     'https://upload.wikimedia.org/wikipedia/commons/f/f4/MiniDachshund1_wb.jpg',
     'DOG', 1, 1001);