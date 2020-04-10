package xyz.taoqz.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.taoqz.User;
import xyz.taoqz.fallback.UserClientFallback;

/**
 * @author :almostTao
 * @date :Created in 2020/4/9 19:42
 */
@FeignClient(value = "web-service",fallback = UserClientFallback.class)
public interface UserClient {

    @GetMapping("query")
    ResponseEntity<User> findUserByMobile(@RequestParam("mobile") String mobile, @RequestParam("password") String password);

}
