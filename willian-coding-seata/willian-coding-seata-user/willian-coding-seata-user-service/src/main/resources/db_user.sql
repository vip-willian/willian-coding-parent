-- auto-generated definition
create table db_user.user_account
(
    id          bigint unsigned auto_increment comment '主键ID'
        primary key,
    user_id     bigint unsigned                    not null comment '用户ID',
    amount      bigint unsigned                    not null comment '账户金额',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uk_user_id
        unique (user_id)
)
    comment '用户账户表';

create index idx_create_time
    on user_account (create_time);

create index idx_update_time
    on user_account (update_time);

