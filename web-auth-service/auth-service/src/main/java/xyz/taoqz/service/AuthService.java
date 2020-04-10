package xyz.taoqz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.taoqz.User;
import xyz.taoqz.feign.UserClient;

/**
 * @author :almostTao
 * @date :Created in 2020/4/9 19:42
 */
@Service
public class AuthService {

    @Autowired
    private UserClient userClient;


    public User findUserByMobile(User user){
        ResponseEntity<User> entity = this.userClient.findUserByMobile(user.getMobile(), user.getPassword());
        User body = entity.getBody();
        return body;
    }


}