package psyco.coder.gen.param;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ParamClass extends ParamBase {
    public String className;
    public List<ParamField> fields;
    public String pack;
    public String classNameLowerCase;

    public ParamClass() {
    }

    public ParamClass(String className, List<ParamField> fields) {
        this.className = className;
        this.fields = fields;
        this.classNameLowerCase = StringUtils.uncapitalize(className);
    }

    public ParamClass(String className, String pack, List<ParamField> fields) {
        this(className, fields);
        this.pack = pack;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ParamField> getFields() {
        return fields;
    }

    public void setFields(List<ParamField> fields) {
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