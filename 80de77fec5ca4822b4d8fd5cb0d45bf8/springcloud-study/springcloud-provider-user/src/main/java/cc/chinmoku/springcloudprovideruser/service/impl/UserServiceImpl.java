package cc.chinmoku.springcloudprovideruser.service.impl;

import cc.chinmoku.springcloudcommon.entity.User;
import cc.chinmoku.springcloudprovideruser.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Override
    public User findById(String id) {
        User user = new User();
        user.setId(id);
        user.setName("User" + id);
        user.setAddress("test");
        user.setSalt("OXKEDI");
        return user;
    }
}
