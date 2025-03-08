create table account
(
    id           bigint auto_increment
        primary key,
    role_code    varchar(10)                            null,
    msisdn       varchar(255)                           null,
    password     varchar(255)                           not null,
    account_name varchar(100)                           not null,
    address      mediumtext                             null,
    image_url    mediumtext                             null,
    created_at   datetime   default current_timestamp() null,
    created_by   varchar(20)                                 null,
    updated_at   datetime                               null on update current_timestamp(),
    updated_by   varchar(20)                                 null,
    deleted      tinyint(1) default 0                   null
)
    collate = utf8mb4_unicode_ci;

create index account_role_fk
    on account (role_code);

create table category
(
    id            bigint auto_increment
        primary key,
    category_name varchar(100)                           not null,
    description   mediumtext                             null,
    image_url     mediumtext                             null,
    created_at    datetime   default current_timestamp() null,
    created_by    varchar(20)                                 null,
    updated_at    datetime                               null on update current_timestamp(),
    updated_by    varchar(20)                                 null,
    deleted       tinyint(1) default 0                   null
)
    collate = utf8mb4_unicode_ci;

create table product
(
    id             bigint auto_increment
        primary key,
    account_id     bigint                                 not null,
    category_id    bigint                                 not null,
    product_name   varchar(100)                           not null,
    description    mediumtext                             null,
    image_url      mediumtext                             null,
    status         mediumtext                             not null,
    created_at     datetime   default current_timestamp() null,
    created_by     varchar(20)                           null,
    updated_at     datetime                               null on update current_timestamp(),
    updated_by     varchar(20)                           null,
    deleted        tinyint(1) default 0                   null,
    original_price double                                 not null,
    selling_price  double                                 not null,
    constraint product_account_fk
        foreign key (account_id) references account (id),
    constraint product_category_fk
        foreign key (category_id) references category (id)
)
    collate = utf8mb4_unicode_ci;

create table cart
(
    id         bigint auto_increment
        primary key,
    account_id bigint               not null,
    product_id bigint               not null,
    quantity   int        default 1 not null,
    created_at datetime(6)          null,
    created_by varchar(20)               null,
    deleted    tinyint(1) default 0 null,
    updated_at datetime(6)          null,
    updated_by varchar(20)               null,
    constraint cart_account_fk
        foreign key (account_id) references account (id),
    constraint cart_product_fk
        foreign key (product_id) references product (id)
)
    collate = utf8mb4_unicode_ci;

create table voucher
(
    id              bigint auto_increment
        primary key,
    voucher_code    varchar(50)                            not null,
    title           varchar(100)                           not null,
    description     mediumtext                             null,
    min_order_value double                                 null,
    coin            double     default 0                   not null,
    image_url       mediumtext                             null,
    started_date    datetime                               not null,
    expiration_date datetime                               not null,
    created_at      datetime   default current_timestamp() null,
    created_by      varchar(20)                                 null,
    updated_at      datetime                               null on update current_timestamp(),
    updated_by      varchar(20)                                 null,
    deleted         tinyint(1) default 0                   null,
    constraint voucher_code
        unique (voucher_code)
)
    collate = utf8mb4_unicode_ci;

create table orders
(
    id             bigint auto_increment
        primary key,
    order_code     varchar(50)                            not null,
    customer_id    bigint                                 not null,
    order_time     datetime   default current_timestamp() null,
    status         varchar(255)                           null,
    voucher_id     bigint                                 null,
    total_discount double     default 0                   null,
    total_fee      double     default 0                   null,
    total_price    double     default 0                   null,
    grand_total    double     default 0                   null,
    created_at     datetime   default current_timestamp() null,
    created_by  varchar(20)                                 null,
    updated_at     datetime                               null on update current_timestamp(),
    updated_by     varchar(20)                                 null,
    deleted        tinyint(1) default 0                   null,
    constraint order_code
        unique (order_code),
    constraint orders_customer_fk
        foreign key (customer_id) references account (id),
    constraint orders_voucher_fk
        foreign key (voucher_id) references voucher (id)
)
    collate = utf8mb4_unicode_ci;

create table order_detail
(
    id          bigint auto_increment
        primary key,
    order_id    bigint                                 not null,
    product_id  bigint                                 not null,
    quantity    int        default 1                   not null,
    unit_price  double                                 not null,
    total_price double                                 not null,
    created_at  datetime   default current_timestamp() null,
    created_by  varchar(20)                                 null,
    updated_at  datetime                               null on update current_timestamp(),
    updated_by  varchar(20)                                 null,
    deleted     tinyint(1) default 0                   null,
    constraint order_detail_order_fk
        foreign key (order_id) references orders (id),
    constraint order_detail_product_fk
        foreign key (product_id) references product (id)
)
    collate = utf8mb4_unicode_ci;

ALTER TABLE account CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE cart CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE category CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE order_detail CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE orders CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE voucher CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE product CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
