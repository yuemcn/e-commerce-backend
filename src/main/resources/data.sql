INSERT INTO product (id, quantity, price, description, image, name, category, is_discontinued, is_featured) VALUES (
    1000,
    10,
    20.00,
    'A nice pair of headphones',
    'https://www.pngarts.com/files/9/Wireless-Headphones-PNG-Image.png',
    'Headphones',
    'electronics',
    false,
    true
),
(
    1001,
    5,
    25.00,
    'A nice TeeShirt',
    'https://d3o2e4jr3mxnm3.cloudfront.net/Mens-Jake-Guitar-Vintage-Crusher-Tee_68382_1_lg.png',
    'TeeShirt',
    'clothing',
    false,
    false
),
(
    1002,
    20,
    2.50,
    'A reusable shopping bag',
    'https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png',
    'Shopping Bag',
    'accessories',
    false,
    false
),
(
    1003,
    20,
    30.00,
    'A fancy cowboy hat for a fancy person',
    'https://www.pngarts.com/files/3/Cowboy-Hat-PNG-Photo.png',
    'Cowboy Hat',
    'clothing',
    false,
    false
),
(
    1004,
    3,
    80.00,
    'A nice coat',
    'https://www.pngarts.com/files/3/Women-Jacket-PNG-High-Quality-Image.png',
    'Coat',
    'clothing',
    false,
    true
),
(
  1005,
  50,
  5.99,
  'Sparkly Cat Ears',
  'https://www.pngarts.com/files/11/Headband-Clip-PNG-Photo.png',
  'Cat Ears',
  'accessories',
  false,
  false
),
(
  1006,
  10,
  9.99,
  'Some Cool Sunglasses',
  'https://www.pngarts.com/files/4/Maui-Jim-Sunglasses-Transparent.png',
  'Sunglasses',
  'accessories',
  false,
  true
),
(
  1007,
  5,
  400,
  'A fancy smart watch',
  'https://www.pngarts.com/files/4/Watch-PNG-Photo.png',
  'Watch',
  'electronics',
  false,
  false
),
(
    1008,
    10,
    100.00,
    'Home Speaker.',
    'https://www.pngarts.com/files/18/Home-Theater-System-Realistic-PNG.png',
    'Speaker',
    'electronics',
    false,
    false
),
(
    1009,
    20,
    60.00,
    'Classy watch.',
    'https://www.pngarts.com/files/4/Watch-Transparent-Image.png',
    'Wrist Watch',
    'accessories',
    false,
    false
),
(
    1010,
    100,
    10.00,
    'Cool foot wear.',
    'https://www.pngarts.com/files/8/Balenciaga-Logo-PNG-Download-Image.png',
    'Sandals',
    'clothing',
    false,
    false
),
(
    1011,
    2,
    80.00,
    'Luggage for travel.',
    'https://www.pngarts.com/files/4/Luggage-Transparent-Image.png',
    'Luggage',
    'accessories',
    false,
    false
),
(
    1012,
    20,
    20.00,
    'A cool hoodie for a cool person',
    'https://www.pngarts.com/files/9/Black-Hoodie-Front-PNG-Transparent-Photo.png',
    'Hoodie',
    'clothing',
    false,
    false
),
(
    1013,
    20,
    12.00,
    'shirt with long sleeves.',
    'https://www.pngarts.com/files/1/T-Shirt-Transparent-Image.png',
    'Long Sleeve Tee',
    'clothing',
    false,
    false
),
(
    1014,
    15,
    8.00,
    'short sleeve t-shirt.',
    'https://www.pngarts.com/files/1/T-Shirt-PNG-High-Quality-Image.png',
    'Tee Shirt',
    'clothing',
    false,
    false
),
(
    1015,
    2,
    40,
    'hand held vacuum cleaner.',
    'https://www.pngarts.com/files/3/Dirt-Vacuum-Cleaner-Transparent-Image.png',
    'Vacuum',
    'electronics',
    false,
    false
),
(
    1016,
    1,
    25.00,
    'A personal fan.',
    'https://www.pngarts.com/files/1/Table-Fan-Transparent-Images.png',
    'Fan',
    'electronics',
    false,
    false
),
(
    1017,
    20,
    20.00,
    'A very nice revature shirt',
    'https://www.pngarts.com/files/5/Plain-Orange-T-Shirt-PNG-Download-Image.png',
    'TeeShirt Rev',
    'clothing',
    false,
    false
);


INSERT INTO users (id, email, password, first_name, last_name, admin) VALUES (
    1000,
    'testuser@gmail.com',
    'password',
    'Test',
    'User',
     false),(
       1001,
         'admin@gmail.com',
         'password',
         'Admin',
         'testAdmin',
          true
);
