create table if not exists service_user (
    user_id varchar(32) primary key,
    password varchar(256) not null
);

create table if not exists work_item (
    user_id varchar(32) not null,
    work_date date not null,
    item_type_name varchar(100) not null,
    item_is_new boolean not null,
    item_unit_price decimal(5, 2) not null,
    item_quantity integer not null,
    primary key (user_id, work_date, item_type_name, item_is_new)
);

create table item_master (
    item_type_name varchar(100) not null,
    item_is_new boolean not null,
    item_unit_price decimal(5, 2) not null,
    primary key (item_type_name, item_is_new)
);
