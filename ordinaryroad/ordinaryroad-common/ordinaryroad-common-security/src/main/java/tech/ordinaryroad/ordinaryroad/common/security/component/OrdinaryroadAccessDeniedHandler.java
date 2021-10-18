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

package tech.ordinaryroad.ordinaryroad.common.security.component;

/**
 * @author lengleng
 * @date 2019/2/1
 */

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import tech.ordinaryroad.ordinaryroad.common.core.constant.CommonConstants;
import tech.ordinaryroad.ordinaryroad.common.core.exception.OrdinaryroadDeniedException;
import tech.ordinaryroad.ordinaryroad.common.core.util.R;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author lengleng 授权拒绝处理器，覆盖默认的OAuth2AccessDeniedHandler 包装失败信息到OrdinaryroadDeniedException
 */
@Slf4j
@RequiredArgsConstructor
public class OrdinaryroadAccessDeniedHandler extends OAuth2AccessDeniedHandler {

	private final ObjectMapper objectMapper;

	/**
	 * 授权拒绝处理，使用R包装
	 * @param request request
	 * @param response response
	 * @param authException authException
	 */
	@Override
	@SneakyThrows
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) {
		log.info("授权失败，禁止访问 {}", request.getRequestURI());
		response.setCharacterEncoding(CommonConstants.UTF8);
		response.setContentType(CommonConstants.CONTENT_TYPE);
		R<OrdinaryroadDeniedException> result = R.failed(new OrdinaryroadDeniedException("授权失败，禁止访问"));
		response.setStatus(HttpStatus.HTTP_FORBIDDEN);
		PrintWriter printWriter = response.getWriter();
		printWriter.append(objectMapper.writeValueAsString(result));
	}

}
