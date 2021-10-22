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
package tech.ordinaryroad.ordinaryroad.blog.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.ordinaryroad.ordinaryroad.blog.api.dto.ArticleDTO;
import tech.ordinaryroad.ordinaryroad.blog.api.entity.BlogArticle;
import tech.ordinaryroad.ordinaryroad.blog.api.entity.BlogArticleTagList;
import tech.ordinaryroad.ordinaryroad.blog.api.entity.BlogTag;
import tech.ordinaryroad.ordinaryroad.blog.api.entity.BlogType;
import tech.ordinaryroad.ordinaryroad.blog.mapper.BlogArticleMapper;
import tech.ordinaryroad.ordinaryroad.blog.mapper.BlogArticleTagListMapper;
import tech.ordinaryroad.ordinaryroad.blog.mapper.BlogTagMapper;
import tech.ordinaryroad.ordinaryroad.blog.mapper.BlogTypeMapper;
import tech.ordinaryroad.ordinaryroad.blog.service.BlogArticleService;
import tech.ordinaryroad.ordinaryroad.blog.service.BlogTagService;
import tech.ordinaryroad.ordinaryroad.blog.service.BlogTypeService;
import tech.ordinaryroad.ordinaryroad.common.security.util.SecurityUtils;

import java.util.List;

/**
 * 文章表
 *
 * @author ordinaryroad code generator
 * @date 2021-10-21 14:34:41
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {

	private final BlogTypeService blogTypeService;
	private final BlogTypeMapper blogTypeMapper;

	private final BlogTagService blogTagService;
	private final BlogTagMapper blogTagMapper;

	private final BlogArticleTagListMapper blogArticleTagListMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveArticle(ArticleDTO articleDTO) {
		BlogArticle blogArticle = new BlogArticle();
		BeanUtils.copyProperties(articleDTO, blogArticle);

		blogArticle.setUserUuid(SecurityUtils.getUser().getUuid());
		if (StrUtil.isBlank(blogArticle.getUuid())) {
			blogArticle.setUuid(IdUtil.fastSimpleUUID());
		}

		// 根据传过来的分类名称查询分类
		String type = articleDTO.getType();
		BlogType blogType = blogTypeMapper.selectOne(Wrappers.<BlogType>query().lambda().eq(BlogType::getName, type));
		if (blogType == null) {
			// 需要创建分类
			blogType = new BlogType();
			blogType.setUuid(IdUtil.fastSimpleUUID());
			blogType.setName(type);
			blogTypeService.save(blogType);
		}
		blogArticle.setTypeUuid(blogType.getUuid());
		this.save(blogArticle);

		List<String> tagList = articleDTO.getTagList();
		if (CollUtil.isNotEmpty(tagList)) {
			tagList.stream().map(tagName -> {
				BlogTag blogTag = blogTagMapper.selectOne(Wrappers.<BlogTag>query().lambda().eq(BlogTag::getName, tagName));
				if (blogTag == null) {
					blogTag = new BlogTag();
					blogTag.setName(tagName);
					blogTagService.save(blogTag);
				}
				BlogArticleTagList entity = new BlogArticleTagList();
				entity.setArticleUuid(blogArticle.getUuid());
				return entity;
			}).forEach(blogArticleTagListMapper::insert);
		}

		return Boolean.TRUE;
	}

}
