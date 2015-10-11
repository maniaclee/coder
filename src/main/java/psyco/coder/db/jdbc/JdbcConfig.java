package psyco.coder.db.jdbc;

import java.io.Serializable;

/**
 * Created by peng on 15/10/11.
 */
public class JdbcConfig implements Serializable {
    String url;
    String user;
    String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
