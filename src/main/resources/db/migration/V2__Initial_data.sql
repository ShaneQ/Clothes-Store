INSERT INTO size (id, name) VALUES
  (1, 'One Size'),
  (2, 'XS'),
  (3, 'S'),
  (4, 'M'),
  (5, 'L'),
  (6, 'XL')
;

INSERT INTO occasion (id, name) VALUES
  (1, 'Casual'),
  (2, 'Cocktail'),
  (3, 'Night out'),
  (4, 'Vacation'),
  (5, 'Work')
;


INSERT INTO product_category (id, name) VALUES
  (1, 'Dresses'),
  (2, 'Tops'),
  (3, 'Pants'),
  (4, 'Skirts'),
  (5, 'Jumpsuits & Rompers'),
  (6, 'Jackets & Coats'),
  (7, 'Bags')
;

INSERT INTO color (id, name) VALUES
  (1, 'black'),
  (2, 'white'),
  (3, 'grey'),
  (4, 'cream'),
  (5, 'brown'),
  (6, 'red'),
  (7, 'orange'),
  (8, 'yellow'),
  (9, 'green'),
  (10, 'blue'),
  (11, 'purple'),
  (12, 'pink'),
  (13, 'gold'),
  (14, 'silver'),
  (15, 'print')
;

INSERT INTO season (id, name) VALUES
  (1, 'Winter'),
  (2, 'Spring'),
  (3, 'Fall'),
  (4, 'Summer')
;

INSERT INTO product_measurement (id, length, chest, hips, waist) VALUES
  (1, 99,100,99,100),
  (2, 99,100,75,33),
  (3, 99,0,99,100),
  (4, 99,64,99,100),
  (5, 99,100,99,0)
;

INSERT INTO image (id, path) VALUES
  (1,'https://valor-software.com/ngx-bootstrap/assets/images/nature/7.jpg'),
  (2,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1kUVuAjGZ8a6N1Tz9m0i0zKXVkk0CTJnJslJu6Z3Pk17XqOdSyQ&s'),
  (3,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1kUVuAjGZ8a6N1Tz9m0i0zKXVkk0CTJnJslJu6Z3Pk17XqOdSyQ&s');
;

INSERT INTO product (id, name, id_product_measurement, id_product_category, id_cover_img, id_season, id_color, dry_clean, retail_price, quick_desc, material, description, fitting_info, wash_info  ) VALUES
  (1,'Dress',1, 1,1,1,1,TRUE, 100 ,'Something Nice and Short', 'cotton','something very long', 'Its tight', 'Dryclean only'),
  (2,'Jumper',2, 2, 1,2,2,FALSE,200,'Something Good','denim','something very long', 'Its loose', 'No spills or i will cut you'),
  (3,'Pants',3, 5, 1,3,3,TRUE, 300, '','silk', 'something very long', 'It weird', 'you wouldnt believe how much this cost dont hand wash'),
  (4,'Sweater',4, 2, 1,4,4, TRUE,400.50,'Cotton', 'Something Else','something very long', 'Its descriptive', 'some other cut and wash info'),
  (5,'Coat',5, 4, 1,4, 10, FALSE, 500,'Something Bad','Nylon','something very long','i will make you feel 30','care for it really well');
;

INSERT INTO product_size (id,id_product, id_size) VALUES
  (1,1, 1),
  (2,2, 2),
  (3,3, 3),
  (4,4, 4),
(5,5, 5),
(6,6, 6),
(7,5, 4)
;

INSERT INTO product_occasion (id,id_product, id_occasion) VALUES
  (1,1, 1),
  (2,1, 2),
  (3,1, 3),
  (4,2, 1),
(5,3, 1),
(6,4, 3),
(7,5, 4)
;

INSERT INTO product_image(id, id_product,id_image) VALUES
(1,1,1),
(2,2,2),
(3,3,3)
;

