package test.dal.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * Created by psyco on 2015/46/23-11:10:22.
 */
public class UserDTO implements Serializable{

    private Long id;
    private String name;
    private Short sex;
    private String email;
    private String phone;
    private Integer level;
    private String imageUrl;
    private String imageThumbUrl;
    private String role;
    private Short enabled;
    private String password;
    private Date gmtCreate;
    private Date gmtLastModified;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getSex() {
        return this.sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageThumbUrl() {
        return this.imageThumbUrl;
    }

    public void setImageThumbUrl(String imageThumbUrl) {
        this.imageThumbUrl = imageThumbUrl;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Short getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Short enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getGmtCreate() {
        return this.gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtLastModified() {
        return this.gmtLastModified;
    }

    public void setGmtLastModified(Date gmtLastModified) {
        this.gmtLastModified = gmtLastModified;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                "sex='" + sex + '\'' +
                "email='" + email + '\'' +
                "phone='" + phone + '\'' +
                "level='" + level + '\'' +
                "imageUrl='" + imageUrl + '\'' +
                "imageThumbUrl='" + imageThumbUrl + '\'' +
                "role='" + role + '\'' +
                "enabled='" + enabled + '\'' +
                "password='" + password + '\'' +
                "gmtCreate='" + gmtCreate + '\'' +
                "gmtLastModified='" + gmtLastModified + '\'' +
                '}';
    }
}
