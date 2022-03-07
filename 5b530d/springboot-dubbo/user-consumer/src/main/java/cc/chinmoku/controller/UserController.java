package cc.chinmoku.controller;

import cc.chinmoku.entity.User;
import cc.chinmoku.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @DubboReference(version = "1.0.0")  //注入要调用的服务
    private UserService userService;

    @RequestMapping("/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable Integer id) {
        //调用服务
        return this.userService.findUserById(id);
    }

}
