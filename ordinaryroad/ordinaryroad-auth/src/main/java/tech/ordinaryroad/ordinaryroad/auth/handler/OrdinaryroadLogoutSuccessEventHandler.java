/*
 * Copyright (c) 2020 ordinaryroad4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.ordinaryroad.ordinaryroad.auth.handler;

import tech.ordinaryroad.ordinaryroad.admin.api.entity.SysLog;
import tech.ordinaryroad.ordinaryroad.common.core.util.SpringContextHolder;
import tech.ordinaryroad.ordinaryroad.common.log.event.SysLogEvent;
import tech.ordinaryroad.ordinaryroad.common.log.util.SysLogUtils;
import tech.ordinaryroad.ordinaryroad.common.security.handler.AbstractLogoutSuccessEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * @author zhangran
 * @date 2021/6/23
 */
@Slf4j
@Component
public class OrdinaryroadLogoutSuccessEventHandler extends AbstractLogoutSuccessEventHandler {

	/**
	 * 处理退出成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 * @param authentication 登录对象
	 */
	@Override
	public void handle(Authentication authentication) {
		log.info("用户：{} 退出成功", authentication.getPrincipal());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		SysLog logVo = SysLogUtils.getSysLog();
		logVo.setTitle("退出成功");
		OAuth2AuthenticationDetails authenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
		logVo.setParams(authenticationDetails == null ? null : authenticationDetails.getTokenValue());
		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Long endTime = System.currentTimeMillis();
		logVo.setTime(endTime - startTime);
		// 这边设置ServiceId
		if (authentication instanceof OAuth2Authentication) {
			OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
			logVo.setServiceId(auth2Authentication.getOAuth2Request().getClientId());
		}
		logVo.setCreateBy(authentication.getName());
		logVo.setUpdateBy(authentication.getName());
		SpringContextHolder.publishEvent(new SysLogEvent(logVo));
	}

}
