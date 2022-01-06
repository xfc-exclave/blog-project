package cc.chinmoku.mongostudy.mongo01;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User insert(User user) {
        return this.mongoTemplate.insert(user);
    }

    @Override
    public User save(User user) {
        return this.mongoTemplate.save(user);
    }

    @Override
    public User update(User user) {
        return this.mongoTemplate.save(user);
    }

    @Override
    public List<User> findAll() {
        return this.mongoTemplate.findAll(User.class);
    }

    @Override
    public boolean remove(User user) {
        DeleteResult result = this.mongoTemplate.remove(user);
        return result.getDeletedCount() > 0;
    }

    @Override
    public List<User> queryById(String id) {
        Criteria criteria1 = Criteria.where("id").is("61caeffbe1dd722f1ac06a07");
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("name").is("zhangsan"), Criteria.where("age").gt(18));
        Query query = Query.query(criteria);
        return this.mongoTemplate.find(query, User.class);
    }

    public List<User> queryByCondition(int age, String name) {
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("age").gt(age), Criteria.where("name").is(name));
        Query query = Query.query(criteria);
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        query.with(sort);
        return this.mongoTemplate.find(query, User.class);
    }

    public List<User> queryWithPage(int index, int limit) {
        Query query = new Query();
        query.skip(index).limit(limit);// 从下标为index开始查询接下来的limit条数据
        return this.mongoTemplate.find(query, User.class);
    }

    public List<User> like(String chars) {
        Query query = new Query(Criteria.where("name").regex(chars));
        return this.mongoTemplate.find(query, User.class);
    }

    public boolean queryToRemove(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        DeleteResult result = this.mongoTemplate.remove(query);
        return result.getDeletedCount() > 0;
    }
}
