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
                                col.getFieldName(),
                                CaseUtil.getter(col.getFieldName()),
                                CaseUtil.setter(col.getFieldName()),
                                col.getJavaType()
                        )).collect(Collectors.toList()),
                tableInfo.getPack())
                .withAuthor(tableInfo.getAuthor());
    }

    public static String exec(TableInfo tableInfo) throws Exception {
        return CoderJavabean.exec(tableInfo(tableInfo));
    }
}
