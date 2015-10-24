package psyco.coder.component.bean;

import psyco.coder.ast.parser.ClassParser;
import psyco.coder.component.jdbc.TableInfo;
import psyco.coder.util.CaseUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by peng on 15/10/24.
 */
public class JavaBeanFactory {

    public static List<JavaBean> instance(String s) throws Exception {
        return ClassParser.extractClasses(s);
    }

    public static JavaBean instance(TableInfo tableInfo) {
        return new JavaBean(
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
}
