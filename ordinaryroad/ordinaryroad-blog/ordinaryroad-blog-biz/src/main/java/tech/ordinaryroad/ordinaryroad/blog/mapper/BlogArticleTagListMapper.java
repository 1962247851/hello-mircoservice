/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the ordinaryroad4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package tech.ordinaryroad.ordinaryroad.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import tech.ordinaryroad.ordinaryroad.blog.api.entity.BlogArticleTagList;

/**
 * 文章标签关联关系表
 *
 * @author ordinaryroad code generator
 * @date 2021-10-21 14:34:41
 */
@Mapper
public interface BlogArticleTagListMapper extends BaseMapper<BlogArticleTagList> {

}
