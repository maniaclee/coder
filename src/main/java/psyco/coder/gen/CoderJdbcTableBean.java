package psyco.coder.gen;

import psyco.coder.util.CaseUtil;
import psyco.coder.db.jdbc.TableInfo;
import psyco.coder.bean.BeanClass;
import psyco.coder.bean.BeanField;

import java.util.stream.Collectors;

/**
 * Created by peng on 15/10/11.
 */
public class CoderJdbcTableBean {

    public static BeanClass tableInfo(TableInfo tableInfo) {
        return new BeanClass(
                tableInfo.getClassName(),
                tableInfo.getColumns().stream().map(col ->
                        new BeanField(
                                col.getColumnName(),
                                CaseUtil.getter(col.getColumnName()),
                                CaseUtil.setter(col.getColumnName()),
                                col.getJavaTyp()
                        )).collect(Collectors.toList()));
    }

    public static String exec(TableInfo tableInfo, String pack) throws Exception {
        return CoderJavabean.exec(tableInfo(tableInfo), pack);
    }
}
