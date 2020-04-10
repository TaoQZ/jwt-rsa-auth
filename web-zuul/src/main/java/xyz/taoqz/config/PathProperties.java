package xyz.taoqz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author :almostTao
 * @date :Created in 2020/4/9 23:22
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "sc.filter")
public class PathProperties {
    private List<String> allowPaths;
}
