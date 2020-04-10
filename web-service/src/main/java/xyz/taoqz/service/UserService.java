package xyz.taoqz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import xyz.taoqz.User;
import xyz.taoqz.mapper.UserMapper;

import java.util.List;

/**
 * @author :almostTao
 * @date :Created in 2020/4/9 20:40
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserByMobileAndPassword(String mobile, String password){
        Example example = new Example(User.class);
        // 1. 手机和密码都必须相等
        example.createCriteria()
                .andEqualTo("mobile",mobile)
                .andEqualTo("password",password);
        // 2. 查询
        List<User> list = userMapper.selectByExample(example);

        return list.size()>0?list.get(0):null;

    }

    public boolean regist(User user) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("name",user.getName())
                .orEqualTo("mobile",user.getMobile());
        User userDB = userMapper.selectOneByExample(example);
        if (userDB == null){
            userMapper.insert(user);
            return true;
        }
        return false;
    }
}
