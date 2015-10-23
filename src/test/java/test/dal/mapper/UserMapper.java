package test.dal.mapper;

import java.util.List;
import java.util.Date;
import test.dal.bean.User;

public interface UserMapper{

    User findOne(Long id);

    List<User> find(List<Long> ids);

    Long insert(User user);

    void update(User user);

    void delete(Long id);

}