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
import tech.ordinaryroad.ordinaryroad.blog.api.entity.BlogTag;
import tech.ordinaryroad.ordinaryroad.blog.service.BlogTagService;
import tech.ordinaryroad.ordinaryroad.common.core.util.R;
import tech.ordinaryroad.ordinaryroad.common.log.annotation.SysLog;


/**
 * 文章标签表
 *
 * @author ordinaryroad code generator
 * @date 2021-10-21 14:34:41
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/blogtag")
@Api(value = "blogtag", tags = "文章标签表管理")
public class BlogTagController {

	private final BlogTagService blogTagService;

	/**
	 * 分页查询
	 *
	 * @param page 分页对象
	 * @param blogTag 文章标签表
	 * @return
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('blog_blogtag_get')")
	public R getBlogTagPage(Page page, BlogTag blogTag) {
		return R.ok(blogTagService.page(page, Wrappers.query(blogTag)));
	}


	/**
	 * 通过id查询文章标签表
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询", notes = "通过id查询")
	@GetMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('blog_blogtag_get')")
	public R getById(@PathVariable("id") Long id) {
		return R.ok(blogTagService.getById(id));
	}

	/**
	 * 新增文章标签表
	 *
	 * @param blogTag 文章标签表
	 * @return R
	 */
	@ApiOperation(value = "新增文章标签表", notes = "新增文章标签表")
	@SysLog("新增文章标签表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('blog_blogtag_add')")
	public R save(@RequestBody BlogTag blogTag) {
		return R.ok(blogTagService.save(blogTag));
	}

	/**
	 * 修改文章标签表
	 *
	 * @param blogTag 文章标签表
	 * @return R
	 */
	@ApiOperation(value = "修改文章标签表", notes = "修改文章标签表")
	@SysLog("修改文章标签表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('blog_blogtag_edit')")
	public R updateById(@RequestBody BlogTag blogTag) {
		return R.ok(blogTagService.updateById(blogTag));
	}

	/**
	 * 通过id删除文章标签表
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id删除文章标签表", notes = "通过id删除文章标签表")
	@SysLog("通过id删除文章标签表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('blog_blogtag_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(blogTagService.removeById(id));
	}

}
