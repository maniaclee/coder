package psyco.coder.gen.param;

import psyco.coder.gen.ParamBase;

import java.util.List;

public class ParamClass extends ParamBase {
    public String className;
    public List<ParamField> fields;

    public ParamClass() {
    }

    public ParamClass(String className, List<ParamField> fields) {
        this.className = className;
        this.fields = fields;
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

    @Override
    public String toString() {
        return "ParamClass{" +
                "className='" + className + '\'' +
                ", fields=" + fields +
                '}';
    }
}