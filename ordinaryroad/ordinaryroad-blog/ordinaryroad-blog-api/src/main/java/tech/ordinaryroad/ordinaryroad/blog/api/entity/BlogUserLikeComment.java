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
package tech.ordinaryroad.ordinaryroad.blog.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.ordinaryroad.ordinaryroad.common.mybatis.base.BaseEntity;

/**
 * 用户点赞评论记录表
 *
 * @author ordinaryroad code generator
 * @date 2021-10-21 14:34:41
 */
@Data
@TableName("blog_user_like_comment")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户点赞评论记录表")
public class BlogUserLikeComment extends BaseEntity {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty(value = "ID")
	private Long id;

	/**
	 * 文章UUID
	 */
	@ApiModelProperty(value = "文章UUID")
	private String articleUuid;

	/**
	 * 是否删除
	 */
	@ApiModelProperty(value = "是否删除")
	private Boolean delFlag;


}
