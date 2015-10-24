package test.dal.mapper;

import java.util.List;
import java.util.Date;
import test.dal.bean.User;
/**
 * Created by psyco on 2015/05/24-12:10:49.
 */
public interface UserMapper{

    User findOne(Long id);

    List<User> find(List<Long> ids);

    Long insert(User user);

    void update(User user);

    void delete(Long id);

}