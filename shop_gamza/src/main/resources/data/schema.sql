DROP DATABASE IF EXISTS gamza_database;
DROP DATABASE IF EXISTS test_gamza_database;

CREATE DATABASE gamza_database;
CREATE DATABASE test_gamza_database;

USE gamza_database;

DROP TABLE IF EXISTS product_images;
DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
    category_id     BIGINT AUTO_INCREMENT,
    name            VARCHAR(20) NOT NULL,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_category_id PRIMARY KEY (category_id)
);

CREATE TABLE products (
    product_id      BIGINT AUTO_INCREMENT,
    name            VARCHAR(20) NOT NULL,
    price           INT NOT NULL,
    description     TEXT,
    selling_type    VARCHAR(20) NOT NULL ,
    stock           INT NOT NULL,
    category_id     BIGINT NOT NULL,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_product_id PRIMARY KEY (product_id),
    CONSTRAINT fk_category_id
        FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

CREATE TABLE images (
    image_id            BIGINT AUTO_INCREMENT,
    upload_file_name    VARCHAR(255),
    store_file_name     VARCHAR(255) NOT NULL,
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_image_id PRIMARY KEY (image_id)
);

CREATE TABLE product_images (
    product_image_id    BIGINT AUTO_INCREMENT,
    is_cover_image      TINYINT DEFAULT false,
    product_id          BIGINT NOT NULL,
    image_id            BIGINT NOT NULL,
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_product_image_id PRIMARY KEY (product_image_id),
    CONSTRAINT fk_product_id
        FOREIGN KEY (product_id) REFERENCES products(product_id),
    CONSTRAINT fk_image_id
        FOREIGN KEY (image_id) REFERENCES images(image_id)
);

#  테스트 데이터 베이스 DDL
USE test_gamza_database;

DROP TABLE IF EXISTS product_images;
DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
    category_id     BIGINT AUTO_INCREMENT,
    name            VARCHAR(20) NOT NULL,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_category_id PRIMARY KEY (category_id)
);

CREATE TABLE products (
    product_id      BIGINT AUTO_INCREMENT,
    name            VARCHAR(20) NOT NULL,
    price           INT NOT NULL,
    description     TEXT,
    selling_type    VARCHAR(20) NOT NULL ,
    stock           INT NOT NULL,
    category_id     BIGINT NOT NULL,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_product_id PRIMARY KEY (product_id),
    CONSTRAINT fk_category_id
      FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

CREATE TABLE images (
    image_id            BIGINT AUTO_INCREMENT,
    upload_file_name    VARCHAR(255),
    store_file_name     VARCHAR(255) NOT NULL,
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_image_id PRIMARY KEY (image_id)
);

CREATE TABLE product_images (
    product_image_id    BIGINT AUTO_INCREMENT,
    is_cover_image      TINYINT DEFAULT false,
    product_id          BIGINT NOT NULL,
    image_id            BIGINT NOT NULL,
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_product_image_id PRIMARY KEY (product_image_id),
    CONSTRAINT fk_product_id
        FOREIGN KEY (product_id) REFERENCES products(product_id),
    CONSTRAINT fk_image_id
        FOREIGN KEY (image_id) REFERENCES images(image_id)
);
