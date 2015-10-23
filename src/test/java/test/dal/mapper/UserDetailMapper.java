package test.dal.mapper;

import java.util.List;
import java.util.Date;
import test.dal.bean.UserDetail;

public interface UserDetailMapper{

    UserDetail findOne(Long id);

    List<UserDetail> find(List<Long> ids);

    Long insert(UserDetail userDetail);

    void update(UserDetail userDetail);

    void delete(Long id);

}