-- auto-generated definition
create table blog_comment
(
    id            bigint auto_increment comment 'ID' primary key,
    uuid          varchar(32)      not null comment 'UUID',
    content       text             not null comment '评论内容',
    sort          int              null comment '评论排序',
    topped        bit default b'0' not null comment '是否置顶',

    # 主键
    original_uuid varchar(32)      null comment '主评论UUID',
    parent_uuid   varchar(32)      null comment '被回复评论UUID',

    # 外键
    user_uuid     varchar(32)      null comment '用户UUID',
    article_uuid  varchar(32)      not null comment '文章UUID',

    # 通用
    create_time   datetime(6)      not null comment '创建时间',
    create_by     varchar(255)     not null comment '创建者',
    update_time   datetime(6)      null comment '更新时间',
    update_by     varchar(255)     null comment '更新者',
    del_flag      bit default b'0' not null comment '是否删除'
)
    comment '评论表' collate = utf8mb4_general_ci;

