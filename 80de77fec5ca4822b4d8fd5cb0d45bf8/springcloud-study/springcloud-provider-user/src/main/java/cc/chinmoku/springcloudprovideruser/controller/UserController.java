package cc.chinmoku.springcloudprovideruser.controller;

import cc.chinmoku.springcloudcommon.common.ResponseObject;
import cc.chinmoku.springcloudcommon.entity.User;
import cc.chinmoku.springcloudprovideruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/detail/{id}")
    public ResponseObject<User> detail(@PathVariable("id") String id) {
        User user = userService.findById(id);
        return new ResponseObject<>("response from port " + port, user);
    }
}
