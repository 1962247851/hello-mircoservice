-- 该脚本不要直接执行， 注意维护菜单的父节点ID 默认 父节点1000

-- 菜单SQL
insert into `sys_menu` ( `parent_id`, `path`, `permission`, `type`, `icon`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '10001', '/blog/blogarticletaglist/index', '', '0', 'icon-bangzhushouji', '0', '2018-01-20 13:17:19', '8', '2018-07-29 13:38:19', '文章标签关联关系表管理');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
insert into `sys_menu` ( `parent_id`, `permission`, `type`, `path`, `icon`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    SELECT @parentId, 'blog_blogarticletaglist_get', '1', null, '1',  '0', '2018-05-15 21:35:18', '0', '2018-07-29 13:38:59', '文章标签关联关系表查看';
insert into `sys_menu` ( `parent_id`, `permission`, `type`, `path`, `icon`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    SELECT @parentId, 'blog_blogarticletaglist_add', '1', null, '1',  '0', '2018-05-15 21:35:18', '1', '2018-07-29 13:38:59', '文章标签关联关系表新增';
insert into `sys_menu` ( `parent_id`, `permission`, `type`, `path`, `icon`,  `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    SELECT @parentId, 'blog_blogarticletaglist_edit', '1', null, '1',  '0', '2018-05-15 21:35:18', '2', '2018-07-29 13:38:59', '文章标签关联关系表修改';
insert into `sys_menu` ( `parent_id`, `permission`, `type`, `path`, `icon`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    SELECT @parentId, 'blog_blogarticletaglist_del', '1', null, '1',  '0', '2018-05-15 21:35:18', '3', '2018-07-29 13:38:59', '文章标签关联关系表删除';

