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

package tech.ordinaryroad.ordinaryroad.admin.api.feign;

import tech.ordinaryroad.ordinaryroad.admin.api.feign.factory.RemoteDeptServiceFallbackFactory;
import tech.ordinaryroad.ordinaryroad.common.core.constant.SecurityConstants;
import tech.ordinaryroad.ordinaryroad.common.core.constant.ServiceNameConstants;
import tech.ordinaryroad.ordinaryroad.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * @author hccake
 */
@FeignClient(contextId = "remoteDeptService", value = ServiceNameConstants.UMPS_SERVICE,
		fallbackFactory = RemoteDeptServiceFallbackFactory.class)
public interface RemoteDeptService {

	/**
	 * 查收子级id列表
	 * @return 返回子级id列表
	 */
	@GetMapping("/dept/child-id/{deptId}")
	R<List<Integer>> listChildDeptId(@PathVariable("deptId") Integer deptId,
			@RequestHeader(SecurityConstants.FROM) String from);

}
