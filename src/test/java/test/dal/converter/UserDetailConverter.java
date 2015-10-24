package test.dal.converter;

import test.dal.bean.UserDetail;
import test.dal.dto.UserDetailDTO;
/**
 * Created by psyco on 2015/05/24-12:10:49.
 */
public class UserDetailConverter{
    public static UserDetailDTO toUserDetailDTO(UserDetail userDetail){
        UserDetailDTO re = new UserDetailDTO();
        re.setId(userDetail.getId());
        re.setUserId(userDetail.getUserId());
        return re;
    }
    public static UserDetail toUserDetail(UserDetailDTO userDetailDTO){
        UserDetail re = new UserDetail();
        re.setId(userDetailDTO.getId());
        re.setUserId(userDetailDTO.getUserId());
        return re;
    }
}
