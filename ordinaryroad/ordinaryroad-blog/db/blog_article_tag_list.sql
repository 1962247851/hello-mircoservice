-- auto-generated definition
create table blog_article_tag_list
(
    id           bigint auto_increment comment 'ID' primary key,
    article_uuid varchar(32)      not null comment '文章UUID',
    tag_uuid     varchar(32)      not null comment '标签UUID',

    create_time  datetime(6)      not null comment '创建时间',
    create_by    varchar(255)     not null comment '创建者',
    update_time  datetime(6)      null comment '更新时间',
    update_by    varchar(255)     null comment '更新者',
    del_flag     bit default b'0' not null comment '是否删除'
)
    comment '文章标签关联关系表' collate = utf8mb4_general_ci;