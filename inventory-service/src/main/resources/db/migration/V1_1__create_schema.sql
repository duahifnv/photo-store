create table product_categories(
    category_code char(3) primary key,
    name varchar(50) not null,
    description text
);

create table products(
    sku_code varchar(30) primary key,
    name varchar(200) not null,
    category_code char(3) references product_categories(category_code)
        on delete set null,
    price numeric(10, 2),
    release_date date not null default CURRENT_DATE
);

create table inventory(
    sku_code varchar(30) references products(sku_code)
                      on delete cascade primary key,
    quantity int default 0
);