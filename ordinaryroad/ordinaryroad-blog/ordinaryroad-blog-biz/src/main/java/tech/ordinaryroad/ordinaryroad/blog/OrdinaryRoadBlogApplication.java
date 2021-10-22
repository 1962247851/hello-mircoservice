package tech.ordinaryroad.ordinaryroad.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tech.ordinaryroad.ordinaryroad.common.feign.annotation.EnableOrdinaryroadFeignClients;
import tech.ordinaryroad.ordinaryroad.common.security.annotation.EnableOrdinaryroadResourceServer;
import tech.ordinaryroad.ordinaryroad.common.swagger.annotation.EnableOrdinaryroadSwagger2;

/**
 * @author pig archetype
 * <p>
 * 项目启动类
 */
@EnableOrdinaryroadSwagger2
@EnableOrdinaryroadFeignClients
@EnableOrdinaryroadResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class OrdinaryRoadBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdinaryRoadBlogApplication.class, args);
	}

}
