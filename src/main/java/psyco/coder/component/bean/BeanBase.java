package psyco.coder.component.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by peng on 15/10/11.
 */
public class BeanBase implements Serializable {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd-hh:MM:ss");
    private String author = "Unknown";
    private String date = sdf.format(new Date());

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
