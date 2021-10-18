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

package tech.ordinaryroad.ordinaryroad.admin;

import tech.ordinaryroad.ordinaryroad.common.feign.annotation.EnableOrdinaryroadFeignClients;
import tech.ordinaryroad.ordinaryroad.common.security.annotation.EnableOrdinaryroadResourceServer;
import tech.ordinaryroad.ordinaryroad.common.swagger.annotation.EnableOrdinaryroadSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lengleng
 * @date 2018年06月21日 用户统一管理系统
 */
@EnableOrdinaryroadSwagger2
@EnableOrdinaryroadResourceServer
@EnableOrdinaryroadFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class OrdinaryroadAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdinaryroadAdminApplication.class, args);
	}

}
