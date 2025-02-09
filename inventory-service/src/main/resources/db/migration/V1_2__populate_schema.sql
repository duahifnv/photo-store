insert into product_categories (category_code, name)
values ('CAM', 'Фотоаппараты'), ('LEN', 'Объективы'), ('FLS', 'Вспышки'), ('OPT', 'Оптические приборы');

insert into products (sku_code, name, category_code, price)
values ('CAM/FUJIF-BL', 'Фотоаппарат Fujifilm X-T5 body черный', 'CAM', 189990.00),
       ('LEN/CANON-BL', 'Объектив Canon RF 16mm f/2.8 STM', 'LEN', 29900.00),
       ('FLS/FUJIF-BL', 'Вспышка Godox MF12 для макросъемки', 'FLS', 11560.00);

insert into inventory (sku_code, quantity)
values ('CAM/FUJIF-BL', 12),
       ('LEN/CANON-BL', 5),
       ('FLS/FUJIF-BL', 3);