create table product_image_filenames(
    sku_code varchar(100) primary key,
    image_type varchar(50) not null,
    image_filename varchar(200) not null
);

create table category_image_filenames(
    category_code char(3) primary key,
    image_type varchar(50) not null,
    image_filename varchar(200) not null
)