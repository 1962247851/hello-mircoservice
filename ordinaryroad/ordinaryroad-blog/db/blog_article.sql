-- auto-generated definition
create table blog_article
(
    id               bigint auto_increment comment 'ID' primary key,
    uuid             varchar(32)      not null comment 'UUID',
    title            varchar(255)     not null comment '文章标题',
    img_url          varchar(500)     null comment '封面地址',
    article_abstract varchar(500)     null comment '文章摘要',
    sort             int              null comment '文章排序',
    content          text             not null comment '文章内容',
    can_comment      bit default b'1' not null comment '是否可以评论',
    can_reward       bit default b'1' not null comment '是否可以打赏',
    original         bit default b'1' not null comment '是否原创',
    shown            bit default b'1' not null comment '是否展示',

    # 外键
    type_uuid        varchar(32)      null comment '文章分类UUID',
    user_uuid        varchar(32)      null comment '用户UUID',
    status           varchar(50)      null comment '文章状态值',

    # 通用
    create_time      datetime(6)      not null comment '创建时间',
    create_by        varchar(255)     not null comment '创建者',
    update_time      datetime(6)      null comment '更新时间',
    update_by        varchar(255)     null comment '更新者',
    del_flag         bit default b'0' not null comment '是否删除'
)
    comment '文章表' collate = utf8mb4_general_ci;