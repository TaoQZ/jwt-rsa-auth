package xyz.taoqz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.taoqz.User;
import xyz.taoqz.service.UserService;
import xyz.taoqz.vo.BaseResult;

/**
 * @author :almostTao
 * @date :Created in 2020/4/9 20:38
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("query")
    public ResponseEntity<User> findUserByMobile(@RequestParam("mobile") String mobile, @RequestParam("password") String password){
        User user = userService.findUserByMobileAndPassword(mobile, password);
        return ResponseEntity.ok(user);
    }

    @GetMapping("demo")
    public ResponseEntity<String> demo (){
//        int i = 1/0;
        return ResponseEntity.ok("demo");
    }

    @PostMapping("/regist")
    public ResponseEntity<BaseResult> regist(@RequestBody User user){
        boolean regist = userService.regist(user);
        BaseResult baseResult = new BaseResult();
        if (!regist){
            baseResult = new BaseResult(1,"账号或手机号已被注册");
        }else {
            baseResult = new BaseResult(0,"注册成功");
        }
        return ResponseEntity.ok(baseResult);
    }

}
