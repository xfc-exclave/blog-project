package cc.chinmoku.mongostudy.mongo01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> findAll() {
        return this.userService.findAll();
    }

    @GetMapping("/find")
    public List<User> find() {
        return this.userService.findAll();
    }

    @PostMapping("/insert")
    public User insert(User user) {
        return this.userService.insert(user);
    }

    @PostMapping("/save")
    public User save(User user) {
        return this.userService.save(user);
    }

    @PostMapping("/update")
    public User update(User user) {
        return this.userService.update(user);
    }

    @PostMapping("/remove")
    public boolean remove(User user) {
        return this.userService.remove(user);
    }
}
