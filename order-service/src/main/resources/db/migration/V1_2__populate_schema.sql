insert into orders (order_id, customer_email)
values ('bf448b59-cf3f-4f2a-9b44-d3f4d3abb86a', 'mfomincev11@gmail.com'),
       ('d87e163b-6529-43cf-8f9b-d016d2651f4f', 'admin@mail.ru');

insert into orders_items (order_id, sku_code, price, quantity)
values ('bf448b59-cf3f-4f2a-9b44-d3f4d3abb86a', 'CAM-FUJIF-BL', 189990.00, 1),
        ('bf448b59-cf3f-4f2a-9b44-d3f4d3abb86a', 'LEN-CANON-BL', 29990.00, 1),
        ('d87e163b-6529-43cf-8f9b-d016d2651f4f', 'LEN-CANON-BL', 29990.00, 2);