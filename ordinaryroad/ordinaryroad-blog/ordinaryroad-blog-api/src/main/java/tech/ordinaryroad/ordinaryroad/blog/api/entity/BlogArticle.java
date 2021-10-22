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
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.ordinaryroad.ordinaryroad.common.mybatis.base.BaseEntity;

/**
 * 文章表
 *
 * @author ordinaryroad code generator
 * @date 2021-10-21 14:34:41
 */
@Data
@TableName("blog_article")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "文章表")
public class BlogArticle extends BaseEntity {

	/**
	 * ID
	 */
	@TableId
	@ApiModelProperty(value = "ID")
	private Long id;

	/**
	 * UUID
	 */
	@ApiModelProperty(value = "UUID")
	private String uuid;

	/**
	 * 文章标题
	 */
	@ApiModelProperty(value = "文章标题")
	private String title;

	/**
	 * 封面地址
	 */
	@ApiModelProperty(value = "封面地址")
	private String imgUrl;

	/**
	 * 文章摘要
	 */
	@ApiModelProperty(value = "文章摘要")
	private String articleAbstract;

	/**
	 * 文章排序
	 */
	@ApiModelProperty(value = "文章排序")
	private Integer sort;

	/**
	 * 文章内容
	 */
	@ApiModelProperty(value = "文章内容")
	private String content;

	/**
	 * 是否可以评论
	 */
	@ApiModelProperty(value = "是否可以评论")
	private Boolean canComment;

	/**
	 * 是否可以打赏
	 */
	@ApiModelProperty(value = "是否可以打赏")
	private Boolean canReward;

	/**
	 * 是否原创
	 */
	@ApiModelProperty(value = "是否原创")
	private Boolean original;

	/**
	 * 是否展示
	 */
	@ApiModelProperty(value = "是否展示")
	private Boolean shown;

	/**
	 * 文章分类UUID
	 */
	@ApiModelProperty(value = "文章分类UUID")
	private String typeUuid;

	/**
	 * 文章状态值
	 */
	@ApiModelProperty(value = "文章状态值")
	private String status;

	/**
	 * 是否删除
	 */
	@TableLogic
	@ApiModelProperty(value = "是否删除")
	private Boolean delFlag;


}
