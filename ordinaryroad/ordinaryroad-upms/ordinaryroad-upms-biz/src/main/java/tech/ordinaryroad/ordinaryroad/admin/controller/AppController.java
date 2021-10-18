package tech.ordinaryroad.ordinaryroad.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import tech.ordinaryroad.ordinaryroad.admin.api.entity.SysUser;
import tech.ordinaryroad.ordinaryroad.admin.service.AppService;
import tech.ordinaryroad.ordinaryroad.admin.service.SysUserService;
import tech.ordinaryroad.ordinaryroad.common.core.util.R;
import tech.ordinaryroad.ordinaryroad.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lengleng
 * @date 2021/9/16 移动端登录
 */
@RestController
@AllArgsConstructor
@RequestMapping("/app")
@Api(value = "app", tags = "手机管理模块")
public class AppController {

	private final AppService appService;

	private final SysUserService userService;

	@Inner(value = false)
	@GetMapping("/{mobile}")
	public R sendSmsCode(@PathVariable String mobile) {
		return appService.sendSmsCode(mobile);
	}

	/**
	 * 获取指定用户全部信息
	 * @param phone 手机号
	 * @return 用户信息
	 */
	@Inner
	@GetMapping("/info/{mobile}")
	public R infoByMobile(@PathVariable String mobile) {
		SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getPhone, mobile));
		if (user == null) {
			return R.failed(String.format("用户信息为空 %s", mobile));
		}
		return R.ok(userService.getUserInfo(user));
	}

}
