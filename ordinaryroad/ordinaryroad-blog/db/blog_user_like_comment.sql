-- auto-generated definition
create table blog_user_like_comment
(
    id           bigint auto_increment comment 'ID' primary key,
    article_uuid varchar(32)      not null comment '文章UUID',
    user_uuid    varchar(23)      not null comment '用户UUID',

    create_time  datetime(6)      not null comment '创建时间',
    create_by    varchar(255)     not null comment '创建者',
    update_time  datetime(6)      null comment '更新时间',
    update_by    varchar(255)     null comment '更新者',
    del_flag     bit default b'0' not null comment '是否删除'
)
    comment '用户点赞评论记录表' collate = utf8mb4_general_ci;