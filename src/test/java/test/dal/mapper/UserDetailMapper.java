package test.dal.mapper;

import java.util.List;
import java.util.Date;
import test.dal.bean.UserDetail;
/**
 * Created by psyco on 2015/19/24-02:10:15.
 */
public interface UserDetailMapper{

    UserDetail findOne(Long id);

    List<UserDetail> find(List<Long> ids);

    Long insert(UserDetail userDetail);

    void update(UserDetail userDetail);

    void delete(Long id);

}