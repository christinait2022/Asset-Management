create table tb_asset
(
    id             bigint not null,
    condition_note varchar(255),
    is_deleted     boolean,
    name           varchar(255),
    purchase_date  timestamp,
    status         varchar(255),
    category_id    bigint,
    employee_id    bigint,
    primary key (id)
);
create table tb_asset_status_change
(
    id             bigint not null,
    condition_note varchar(255),
    create_date    timestamp,
    report_status  varchar(255),
    asset_id       bigint,
    employee_id    bigint,
    primary key (id)
);
create table tb_category
(
    id          bigint not null,
    create_date timestamp,
    description varchar(255),
    is_deleted  boolean,
    name        varchar(255),
    primary key (id)
);
create table tb_employee
(
    id          bigint not null,
    designation varchar(255),
    full_name   varchar(255),
    primary key (id)
);
create sequence hibernate_sequence start with 1 increment by 1;
alter table tb_asset
    add constraint FKoth8pg4cpsscwjwfnohd66yd1 foreign key (category_id) references tb_category;
alter table tb_asset
    add constraint FK4r51k3ees4f6owwhdx0mojrln foreign key (employee_id) references tb_employee;
alter table tb_asset_status_change
    add constraint FKfbb2yq7iyolnugtlwy3d4bexv foreign key (asset_id) references tb_asset;
alter table tb_asset_status_change
    add constraint FKapnvtgutsvet5lvg4nhv23m85 foreign key (employee_id) references tb_employee;