package xyz.taoqz.fallback;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import xyz.taoqz.User;
import xyz.taoqz.feign.UserClient;

/**
 * @author :almostTao
 * @date :Created in 2020/4/9 19:43
 */
@Component
public class UserClientFallback implements UserClient {
    @Override
    public ResponseEntity<User> findUserByMobile(String mobile, String password) {
        User user = new User();
        user.setId(-100);
        return ResponseEntity.ok(user);
    }
}
