package tech.ordinaryroad.ordinaryroad.blog.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.ordinaryroad.ordinaryroad.blog.api.entity.BlogArticle;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 文章DTO类
 *
 * @author mjz
 * @date 2021/10/21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleDTO extends BlogArticle {

	/**
	 * 文章分类名称
	 */
	@NotBlank(message = "文章分类名称不能为空")
	private String type;

	/**
	 * 文章标签名称列表
	 */
	private List<String> tagList;

}
