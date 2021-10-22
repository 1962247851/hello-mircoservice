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
import tech.ordinaryroad.ordinaryroad.blog.api.entity.BlogComment;
import tech.ordinaryroad.ordinaryroad.blog.service.BlogCommentService;
import tech.ordinaryroad.ordinaryroad.common.core.util.R;
import tech.ordinaryroad.ordinaryroad.common.log.annotation.SysLog;


/**
 * 评论表
 *
 * @author ordinaryroad code generator
 * @date 2021-10-21 14:34:41
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/blogcomment")
@Api(value = "blogcomment", tags = "评论表管理")
public class BlogCommentController {

	private final BlogCommentService blogCommentService;

	/**
	 * 分页查询
	 *
	 * @param page 分页对象
	 * @param blogComment 评论表
	 * @return
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@pms.hasPermission('blog_blogcomment_get')")
	public R getBlogCommentPage(Page page, BlogComment blogComment) {
		return R.ok(blogCommentService.page(page, Wrappers.query(blogComment)));
	}


	/**
	 * 通过id查询评论表
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询", notes = "通过id查询")
	@GetMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('blog_blogcomment_get')")
	public R getById(@PathVariable("id") Long id) {
		return R.ok(blogCommentService.getById(id));
	}

	/**
	 * 新增评论表
	 *
	 * @param blogComment 评论表
	 * @return R
	 */
	@ApiOperation(value = "新增评论表", notes = "新增评论表")
	@SysLog("新增评论表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('blog_blogcomment_add')")
	public R save(@RequestBody BlogComment blogComment) {
		return R.ok(blogCommentService.save(blogComment));
	}

	/**
	 * 修改评论表
	 *
	 * @param blogComment 评论表
	 * @return R
	 */
	@ApiOperation(value = "修改评论表", notes = "修改评论表")
	@SysLog("修改评论表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('blog_blogcomment_edit')")
	public R updateById(@RequestBody BlogComment blogComment) {
		return R.ok(blogCommentService.updateById(blogComment));
	}

	/**
	 * 通过id删除评论表
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id删除评论表", notes = "通过id删除评论表")
	@SysLog("通过id删除评论表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('blog_blogcomment_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(blogCommentService.removeById(id));
	}

}
