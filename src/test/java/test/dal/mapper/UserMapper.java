package test.dal.mapper;

import test.dal.bean.User;

import java.util.List;
/**
 * Created by psyco on 2015/23/24-02:10:09.
 */
public interface UserMapper{

    User findOne(Long id);

    List<User> find(List<Long> ids);

    Long insert(User user);

    void update(User user);

    void delete(Long id);

}