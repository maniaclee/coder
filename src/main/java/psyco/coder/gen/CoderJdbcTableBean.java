package psyco.coder.gen;

import psyco.coder.ast.util.CaseUtil;
import psyco.coder.db.jdbc.TableInfo;
import psyco.coder.gen.param.ParamClass;
import psyco.coder.gen.param.ParamField;

import java.util.stream.Collectors;

/**
 * Created by peng on 15/10/11.
 */
public class CoderJdbcTableBean {

    public static ParamClass tableInfo(TableInfo tableInfo) {
        return new ParamClass(
                tableInfo.getClassName(),
                tableInfo.getColumns().stream().map(col ->
                        new ParamField(
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
