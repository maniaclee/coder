package test.dal.repo;

import test.dal.bean.User;
import test.dal.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by peng on 15/10/24.
 */
public class UserRepository {
    @Resource
    private UserMapper userMapper;

    public User findOne(Long id) {
        return userMapper.findOne(id);
    }

    public Long insert(User user) {
        return userMapper.insert(user);
    }

    public List<User> find(List<Long> ids) {
        return userMapper.find(ids);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public void delete(Long id) {
        userMapper.delete(id);
    }
}
