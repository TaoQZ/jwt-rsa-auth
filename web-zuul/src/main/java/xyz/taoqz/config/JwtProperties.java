package xyz.taoqz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import xyz.taoqz.util.RsaUtils;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

/**
 * @author :almostTao
 * @date :Created in 2020/4/9 20:35
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "sc.jwt")
public class JwtProperties {

    private String pubKeyPath;// 公钥

    private PublicKey publicKey; // 公钥

    @PostConstruct
    public void init(){
        try {
            // 获取公钥和私钥
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
