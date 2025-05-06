create table orders(
    order_id uuid primary key,
    customer_email varchar(100) not null,
    order_timestamp timestamp not null default CURRENT_TIMESTAMP
);

create table orders_items(
    item_id bigserial primary key,
    order_id uuid not null references orders(order_id),
    sku_code varchar(30) not null,
    price numeric(10, 2) not null,
    quantity int not null
)