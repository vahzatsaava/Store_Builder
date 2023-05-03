create table if not exists users
(
    id        bigint auto_increment,
    user_name varchar(255) not null,
    status    varchar(255) default 'ACTIVE',
    email     varchar(255) not null,
    name      varchar(255) not null,
    password  varchar(255) not null,
    phone     varchar(255) not null,
    unique (email),
    unique (phone),
    unique (user_name),
    primary key (id)
);
create table if not exists role
(
    id   bigint auto_increment,
    name varchar(255),
    primary key (id)
);

CREATE TABLE if not exists product
(
    id    BIGINT PRIMARY KEY auto_increment,
    name  VARCHAR(255),
    price DECIMAL(10, 2),
    url   varchar(250),
    category varchar(250)
);
CREATE TABLE if not exists keeproom
(
    id         BIGINT PRIMARY KEY auto_increment,
    product_id BIGINT,
    quantity   INT,
    FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE if not exists order_table
(
    id         BIGINT PRIMARY KEY auto_increment,
    user_id    BIGINT,
    order_date DATE,
    FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE TABLE if not exists order_item
(
    id         BIGINT PRIMARY KEY auto_increment,
    order_id   BIGINT,
    product_id BIGINT,
    quantity   INT,
    FOREIGN KEY (order_id) REFERENCES order_table (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);
create table if not exists user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    foreign key (user_id) references users (id),
    foreign key (role_id) references role (id)
)
