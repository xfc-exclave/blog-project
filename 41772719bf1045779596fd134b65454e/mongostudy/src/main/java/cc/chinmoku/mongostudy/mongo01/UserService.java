package cc.chinmoku.mongostudy.mongo01;

import java.util.List;

public interface UserService {

    User insert(User user);

    User save(User user);

    User update(User user);

    List<User> findAll();

    boolean remove(User user);

    List<User> queryById(String id);
}
