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
import tech.ordinaryroad.ordinaryroad.blog.api.entity.BlogType;
import tech.ordinaryroad.ordinaryroad.blog.service.BlogTypeService;
import tech.ordinaryroad.ordinaryroad.common.core.util.R;
import tech.ordinaryroad.ordinaryroad.common.log.annotation.SysLog;


/**
 * 文章分类表
 *
 * @author ordinaryroad code generator
 * @date 2021-10-21 14:34:41
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/blogtype")
@Api(value = "blogtype", tags = "文章分类表管理")
public class BlogTypeController {

	private final BlogTypeService blogTypeService;

	/**
	 * 分页查询
	 *
	 * @param page 分页对象
	 * @param blogType 文章分类表
	 * @return
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('blog_blogtype_get')")
	public R getBlogTypePage(Page page, BlogType blogType) {
		return R.ok(blogTypeService.page(page, Wrappers.query(blogType)));
	}


	/**
	 * 通过id查询文章分类表
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询", notes = "通过id查询")
	@GetMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('blog_blogtype_get')")
	public R getById(@PathVariable("id") Long id) {
		return R.ok(blogTypeService.getById(id));
	}

	/**
	 * 新增文章分类表
	 *
	 * @param blogType 文章分类表
	 * @return R
	 */
	@ApiOperation(value = "新增文章分类表", notes = "新增文章分类表")
	@SysLog("新增文章分类表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('blog_blogtype_add')")
	public R save(@RequestBody BlogType blogType) {
		return R.ok(blogTypeService.save(blogType));
	}

	/**
	 * 修改文章分类表
	 *
	 * @param blogType 文章分类表
	 * @return R
	 */
	@ApiOperation(value = "修改文章分类表", notes = "修改文章分类表")
	@SysLog("修改文章分类表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('blog_blogtype_edit')")
	public R updateById(@RequestBody BlogType blogType) {
		return R.ok(blogTypeService.updateById(blogType));
	}

	/**
	 * 通过id删除文章分类表
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id删除文章分类表", notes = "通过id删除文章分类表")
	@SysLog("通过id删除文章分类表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('blog_blogtype_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(blogTypeService.removeById(id));
	}

}
