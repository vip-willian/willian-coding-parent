-- auto-generated definition
create table db_goods.goods
(
    id              bigint unsigned auto_increment comment '商品ID'
        primary key,
    goods_type      tinyint                            not null comment '商品类型',
    goods_name      varchar(500)                       not null comment '商品名称',
    goods_price     decimal(10, 6)                     not null comment '商品价格',
    goods_inventory int(4)                             not null comment '商品库存',
    create_time     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '商品表';

create index idx_create_time
    on goods (create_time);

create index idx_update_time
    on goods (update_time);

