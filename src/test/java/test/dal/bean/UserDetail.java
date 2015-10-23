package test.dal.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * Created by psyco on 2015/19/24-02:10:15.
 */
public class UserDetail implements Serializable{

    private Long id;
    private Long userId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id='" + id + '\'' +
                "userId='" + userId + '\'' +
                '}';
    }
}
