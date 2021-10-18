package tech.ordinaryroad.ordinaryroad.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;

/**
 * @author lengleng
 * @date 2020/10/4
 * <p>
 * 网关配置文件
 */
@Data
@RefreshScope
@ConfigurationProperties("gateway")
public class GatewayConfigProperties {

	/**
	 * 网关解密登录前端密码 秘钥 {@link tech.ordinaryroad.ordinaryroad.gateway.filter.PasswordDecoderFilter}
	 */
	private String encodeKey;

	/**
	 * 网关不需要校验验证码的客户端 {@link tech.ordinaryroad.ordinaryroad.gateway.filter.ValidateCodeGatewayFilter}
	 */
	private List<String> ignoreClients;

}
