create table db_order.order
(
    id           bigint unsigned                    not null auto_increment primary key comment '订单ID',
    user_id      bigint unsigned                    not null comment '用户ID',
    total_price  decimal(10, 6)                     not null comment '订单总价',
    order_status tinyint(4)                         not null comment '订单状态',
    pay_time     datetime                           null comment '支付时间',
    create_time  datetime default current_timestamp not null comment '创建时间',
    update_time  datetime default current_timestamp not null comment '更新时间' on update current_timestamp,
    key `idx_user_id` (user_id),
    key `idx_create_time` (create_time),
    key `idx_update_time` (update_time)
) comment ='订单表';

create table db_order.order_detail
(
    id          bigint unsigned                    not null auto_increment primary key comment '主键ID',
    user_id     bigint unsigned                    not null comment '用户ID',
    order_id    bigint unsigned                    not null comment '订单ID',
    goods_id    bigint unsigned                    not null comment '商品ID',
    unit_price  decimal(10, 6)                     not null comment '购买单价',
    quantity    int(4)                             not null comment '购买数量',
    create_time datetime default current_timestamp not null comment '创建时间',
    update_time datetime default current_timestamp not null comment '更新时间' on update current_timestamp,
    unique key `idx_user_order_goods_id` (user_id, order_id, goods_id),
    key `idx_user_id` (user_id),
    key `idx_order_id` (order_id),
    key `idx_create_time` (create_time),
    key `idx_update_time` (update_time)
) comment ='订单详情表';