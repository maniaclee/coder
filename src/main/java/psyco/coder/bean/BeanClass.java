package psyco.coder.bean;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class BeanClass extends BeanBase {
    public String className;
    public List<BeanField> fields;
    public String pack;
    public String classNameLowerCase;

    public BeanClass() {
    }

    public BeanClass(String className, List<BeanField> fields) {
        this.className = className;
        this.fields = fields;
        this.classNameLowerCase = StringUtils.uncapitalize(className);
    }

    public BeanClass(String className, String pack, List<BeanField> fields) {
        this(className, fields);
        this.pack = pack;
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