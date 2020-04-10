package xyz.taoqz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author :almostTao
 * @date :Created in 2020/4/9 20:51
 */
@EnableEurekaServer
@SpringBootApplication
public class WebEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebEurekaServerApplication.class,args);
    }
}
