package test.dal.converter;

import test.dal.bean.User;
import test.dal.dto.UserDTO;
/**
 * Created by psyco on 2015/05/24-12:10:49.
 */
public class UserConverter{
    public static UserDTO toUserDTO(User user){
        UserDTO re = new UserDTO();
        re.setId(user.getId());
        re.setName(user.getName());
        re.setSex(user.getSex());
        re.setEmail(user.getEmail());
        re.setPhone(user.getPhone());
        re.setLevel(user.getLevel());
        re.setImageUrl(user.getImageUrl());
        re.setImageThumbUrl(user.getImageThumbUrl());
        re.setRole(user.getRole());
        re.setEnabled(user.getEnabled());
        re.setPassword(user.getPassword());
        re.setGmtCreate(user.getGmtCreate());
        re.setGmtLastModified(user.getGmtLastModified());
        return re;
    }
    public static User toUser(UserDTO userDTO){
        User re = new User();
        re.setId(userDTO.getId());
        re.setName(userDTO.getName());
        re.setSex(userDTO.getSex());
        re.setEmail(userDTO.getEmail());
        re.setPhone(userDTO.getPhone());
        re.setLevel(userDTO.getLevel());
        re.setImageUrl(userDTO.getImageUrl());
        re.setImageThumbUrl(userDTO.getImageThumbUrl());
        re.setRole(userDTO.getRole());
        re.setEnabled(userDTO.getEnabled());
        re.setPassword(userDTO.getPassword());
        re.setGmtCreate(userDTO.getGmtCreate());
        re.setGmtLastModified(userDTO.getGmtLastModified());
        return re;
    }
}
