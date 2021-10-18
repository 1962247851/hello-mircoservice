package tech.ordinaryroad.ordinaryroad.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import tech.ordinaryroad.ordinaryroad.gateway.filter.PasswordDecoderFilter;
import tech.ordinaryroad.ordinaryroad.gateway.filter.OrdinaryroadRequestGlobalFilter;
import tech.ordinaryroad.ordinaryroad.gateway.filter.ValidateCodeGatewayFilter;
import tech.ordinaryroad.ordinaryroad.gateway.handler.GlobalExceptionHandler;
import tech.ordinaryroad.ordinaryroad.gateway.handler.ImageCodeHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 网关配置
 *
 * @author L.cm
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(GatewayConfigProperties.class)
public class GatewayConfiguration {

	@Bean
	public PasswordDecoderFilter passwordDecoderFilter(GatewayConfigProperties configProperties) {
		return new PasswordDecoderFilter(configProperties);
	}

	@Bean
	public OrdinaryroadRequestGlobalFilter ordinaryroadRequestGlobalFilter() {
		return new OrdinaryroadRequestGlobalFilter();
	}

	@Bean
	public ValidateCodeGatewayFilter validateCodeGatewayFilter(GatewayConfigProperties configProperties,
			ObjectMapper objectMapper, RedisTemplate redisTemplate) {
		return new ValidateCodeGatewayFilter(configProperties, objectMapper, redisTemplate);
	}

	@Bean
	public GlobalExceptionHandler globalExceptionHandler(ObjectMapper objectMapper) {
		return new GlobalExceptionHandler(objectMapper);
	}

	@Bean
	public ImageCodeHandler imageCodeHandler(RedisTemplate redisTemplate) {
		return new ImageCodeHandler(redisTemplate);
	}

}
