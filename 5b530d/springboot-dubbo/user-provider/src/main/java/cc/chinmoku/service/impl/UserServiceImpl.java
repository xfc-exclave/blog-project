package cc.chinmoku.service.impl;

import cc.chinmoku.entity.User;
import cc.chinmoku.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0")
public class UserServiceImpl implements UserService {

    public User findUserById(int id) {
        User user = new User();
        user.setId(id);
        user.setUsername("user-" + id);
        user.setAge(18);
        user.setTel("13322223333");
        return user;
    }
}
