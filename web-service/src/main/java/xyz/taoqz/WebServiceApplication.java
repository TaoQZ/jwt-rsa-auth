package xyz.taoqz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author :almostTao
 * @date :Created in 2020/4/9 20:48
 */
@EnableEurekaClient
@MapperScan("xyz.taoqz.mapper")
@SpringBootApplication
public class WebServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class,args);
    }
}
