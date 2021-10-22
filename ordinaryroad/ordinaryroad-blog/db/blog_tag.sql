-- auto-generated definition
create table blog_tag
(
    id          bigint auto_increment comment 'ID' primary key,
    uuid        varchar(32)      not null comment 'UUID',
    name        varchar(50)      not null comment '标签名称',
    sort        int              null comment '标签排序',
    shown       bit default b'1' not null comment '是否展示',

    # 外键
    user_uuid   varchar(32)      null comment '用户UUID',

    create_time datetime(6)      not null comment '创建时间',
    create_by   varchar(255)     not null comment '创建者',
    update_time datetime(6)      null comment '更新时间',
    update_by   varchar(255)     null comment '更新者',
    del_flag    bit default b'0' not null comment '是否删除'
)
    comment '文章标签表' collate = utf8mb4_general_ci;