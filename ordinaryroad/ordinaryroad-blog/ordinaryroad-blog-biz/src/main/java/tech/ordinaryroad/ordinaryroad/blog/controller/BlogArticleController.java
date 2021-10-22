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

package tech.ordinaryroad.ordinaryroad.blog.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.ordinaryroad.ordinaryroad.blog.api.dto.ArticleDTO;
import tech.ordinaryroad.ordinaryroad.blog.api.entity.BlogArticle;
import tech.ordinaryroad.ordinaryroad.blog.service.BlogArticleService;
import tech.ordinaryroad.ordinaryroad.common.core.util.R;
import tech.ordinaryroad.ordinaryroad.common.log.annotation.SysLog;

import javax.validation.Valid;


/**
 * 文章表
 *
 * @author ordinaryroad code generator
 * @date 2021-10-21 14:34:41
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/blogarticle")
@Api(value = "blogarticle", tags = "文章表管理")
public class BlogArticleController {

	private final BlogArticleService blogArticleService;

	/**
	 * 分页查询
	 *
	 * @param page 分页对象
	 * @param blogArticle 文章表
	 * @return
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('blog_blogarticle_get')")
	public R getBlogArticlePage(Page page, BlogArticle blogArticle) {
		return R.ok(blogArticleService.page(page, Wrappers.query(blogArticle)));
	}


	/**
	 * 通过id查询文章表
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询", notes = "通过id查询")
	@GetMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('blog_blogarticle_get')")
	public R getById(@PathVariable("id") Long id) {
		return R.ok(blogArticleService.getById(id));
	}

	/**
	 * 新增文章表
	 *
	 * @param blogArticle 文章表
	 * @return R
	 */
	@ApiOperation(value = "新增文章表", notes = "新增文章表")
	@SysLog("新增文章表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('blog_blogarticle_add')")
	public R save(@Valid @RequestBody ArticleDTO articleDTO) {
		return R.ok(blogArticleService.saveArticle(articleDTO));
	}

	/**
	 * 修改文章表
	 *
	 * @param blogArticle 文章表
	 * @return R
	 */
	@ApiOperation(value = "修改文章表", notes = "修改文章表")
	@SysLog("修改文章表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('blog_blogarticle_edit')")
	public R updateById(@RequestBody BlogArticle blogArticle) {
		return R.ok(blogArticleService.updateById(blogArticle));
	}

	/**
	 * 通过id删除文章表
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id删除文章表", notes = "通过id删除文章表")
	@SysLog("通过id删除文章表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('blog_blogarticle_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(blogArticleService.removeById(id));
	}

}
