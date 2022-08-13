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
    (4, 1 , 'HotDog' , 'It can always end up in the dog.. :) And we put a lot of volume in the comment section so we can check and test the UI and if it functions correct. This is for testing purposes only so bear in mind that !',
     'Sausagy',
     'https://upload.wikimedia.org/wikipedia/commons/f/f4/MiniDachshund1_wb.jpg',
     'DOG', 1, 1001);

--some foods
INSERT INTO foods (id, brand, description, price, type, picture_url )
VALUES
(1, 'JOSERA' , 'Really nice and healthy foods for your favorite pet. Festival Edition', 125, 'Dry',
 'https://www.josera.com/uploads/media/content-half/06/106-josera-festival-dog-food-package.png?v=3-0' ),
(2, 'JOSERA' , 'Best healthy food for your favorite pet. Family plus package.', 132, 'Dry',
 'https://www.josera.com/uploads/media/content-half/00/40-josera-family-plus-dog-food-package.png?v=3-0' ),
(3, 'PEDIGREE' , 'Not the best you can get, but a budget one. ', 101, 'Dry',
 'https://m.media-amazon.com/images/I/81xyE8OZBqL._AC_SX466_.jpg' ),
(4, 'GOSBY' , 'Spanish and grain free protein. Best for sensitive stomach of the pet.', 143, 'Dry',
 'https://gosbi.com/wp-content/uploads/2016/05/Grain_free_Puppy_72_dpi.png' );

--some sample comments
INSERT INTO comments(id, username, comment)
VALUES
    (1, 'example@example', 'Some test comment for example'),
    (2, 'example@example', 'Some test 2 comment for example'),
    (3, 'example@example', 'Some test 3 comment for example');

