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
import tech.ordinaryroad.ordinaryroad.blog.api.entity.BlogArticleTagList;
import tech.ordinaryroad.ordinaryroad.blog.service.BlogArticleTagListService;
import tech.ordinaryroad.ordinaryroad.common.core.util.R;
import tech.ordinaryroad.ordinaryroad.common.log.annotation.SysLog;


/**
 * 文章标签关联关系表
 *
 * @author ordinaryroad code generator
 * @date 2021-10-21 14:34:41
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/blogarticletaglist")
@Api(value = "blogarticletaglist", tags = "文章标签关联关系表管理")
public class BlogArticleTagListController {

	private final BlogArticleTagListService blogArticleTagListService;

	/**
	 * 分页查询
	 *
	 * @param page 分页对象
	 * @param blogArticleTagList 文章标签关联关系表
	 * @return
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('blog_blogarticletaglist_get')")
	public R getBlogArticleTagListPage(Page page, BlogArticleTagList blogArticleTagList) {
		return R.ok(blogArticleTagListService.page(page, Wrappers.query(blogArticleTagList)));
	}


	/**
	 * 通过id查询文章标签关联关系表
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询", notes = "通过id查询")
	@GetMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('blog_blogarticletaglist_get')")
	public R getById(@PathVariable("id") Long id) {
		return R.ok(blogArticleTagListService.getById(id));
	}

	/**
	 * 新增文章标签关联关系表
	 *
	 * @param blogArticleTagList 文章标签关联关系表
	 * @return R
	 */
	@ApiOperation(value = "新增文章标签关联关系表", notes = "新增文章标签关联关系表")
	@SysLog("新增文章标签关联关系表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('blog_blogarticletaglist_add')")
	public R save(@RequestBody BlogArticleTagList blogArticleTagList) {
		return R.ok(blogArticleTagListService.save(blogArticleTagList));
	}

	/**
	 * 修改文章标签关联关系表
	 *
	 * @param blogArticleTagList 文章标签关联关系表
	 * @return R
	 */
	@ApiOperation(value = "修改文章标签关联关系表", notes = "修改文章标签关联关系表")
	@SysLog("修改文章标签关联关系表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('blog_blogarticletaglist_edit')")
	public R updateById(@RequestBody BlogArticleTagList blogArticleTagList) {
		return R.ok(blogArticleTagListService.updateById(blogArticleTagList));
	}

	/**
	 * 通过id删除文章标签关联关系表
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id删除文章标签关联关系表", notes = "通过id删除文章标签关联关系表")
	@SysLog("通过id删除文章标签关联关系表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('blog_blogarticletaglist_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(blogArticleTagListService.removeById(id));
	}

}
