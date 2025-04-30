CREATE EXTENSION IF NOT EXISTS pgcrypto;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table product_categories(
    category_code char(3) primary key,
    name varchar(50) not null,
    description text
);

CREATE TABLE products (
      sku_code VARCHAR(30) PRIMARY KEY,
      name VARCHAR(200) NOT NULL,
      category_code CHAR(3) REFERENCES product_categories(category_code) ON DELETE SET NULL,
      price NUMERIC(10, 2),
      release_date DATE NOT NULL DEFAULT CURRENT_DATE,
      properties JSONB DEFAULT '{}'::JSONB
);

create table inventory(
    sku_code varchar(30) references products(sku_code)
                      on delete cascade primary key,
    quantity int default 0
);