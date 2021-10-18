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

package tech.ordinaryroad.ordinaryroad.admin.api.feign.fallback;

import tech.ordinaryroad.ordinaryroad.admin.api.dto.UserInfo;
import tech.ordinaryroad.ordinaryroad.admin.api.feign.RemoteUserService;
import tech.ordinaryroad.ordinaryroad.common.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@Slf4j
@Component
public class RemoteUserServiceFallbackImpl implements RemoteUserService {

	@Setter
	private Throwable cause;

	/**
	 * 通过用户名查询用户、角色信息
	 * @param username 用户名
	 * @param from 内外标志
	 * @return R
	 */
	@Override
	public R<UserInfo> info(String username, String from) {
		log.error("feign 查询用户信息失败:{}", username, cause);
		return null;
	}

	/**
	 * 通过手机号码查询用户、角色信息
	 * @param phone 手机号码
	 * @param from 调用标志
	 * @return R
	 */
	@Override
	public R<UserInfo> infoByMobile(String mobile, String from) {
		log.error("feign 查询用户信息失败手机号码:{}", mobile, cause);
		return null;
	}

	@Override
	public R<List<Integer>> listUserIdByDeptIds(Set<Integer> deptIds, String from) {
		log.error("feign 根据部门ids查询用户Id集合失败:{}", deptIds, cause);
		return null;
	}

}
