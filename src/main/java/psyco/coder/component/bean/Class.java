package psyco.coder.component.bean;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import psyco.coder.ast.parser.ClassParser;
import psyco.coder.component.Base;
import psyco.coder.util.CaseUtil;

import java.util.List;
import java.util.stream.Collectors;

public class Class extends Base {
    public String className;
    public List<BeanField> fields;
    public String pack;
    public String classNameLowerCase;

    public Class() {
    }

    public Class(String className, List<BeanField> fields) {
        this.className = className;
        this.fields = fields;
        this.classNameLowerCase = StringUtils.uncapitalize(className);
    }

    public Class(String className, List<BeanField> fields, String pack) {
        this(className, fields);
        this.pack = pack;
    }


    public static Class parseBeanExtended(String s, java.lang.Class<? extends Class> paramClass) throws Exception {
        ClassParser ast = ClassParser.parse(s);
        TypeDeclaration clz = ast.findClass();
        return paramClass.getConstructor(String.class, List.class)
                .newInstance(clz.getName().toString(),
                        ast.fields(clz).stream().map(f -> {
                                    String fn = ClassParser.getFieldName(f);
                                    return new BeanField(fn, CaseUtil.setter(fn), CaseUtil.getter(fn), f.getType().toString());
                                }
                        ).collect(Collectors.toList()));
    }

    public Class withAuthor(String author) {
        setAuthor(author);
        return this;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<BeanField> getFields() {
        return fields;
    }

    public void setFields(List<BeanField> fields) {
        this.fields = fields;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        if (pack != null && !pack.trim().isEmpty())
            this.pack = pack;
    }

    public String getClassNameLowerCase() {
        return classNameLowerCase;
    }

    public void setClassNameLowerCase(String classNameLowerCase) {
        this.classNameLowerCase = classNameLowerCase;
    }
}