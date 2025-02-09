create table orders(
    order_id uuid primary key,
    sku_code varchar(30) not null,
    price numeric(10, 2) not null,
    quantity int not null,
    customer_email varchar(100) not null,
    order_timestamp timestamp not null default CURRENT_TIMESTAMP
);