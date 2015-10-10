package psyco.coder.ast.gen;

import java.util.List;

public class BuilderParam extends ParamBase{
    public  String className;
    public  String builderClassName;
    public List<Field> fields;

    public BuilderParam() {
    }

    public BuilderParam(String className, String builderClassName, List<Field> fields) {
        this.className = className;
        this.builderClassName = builderClassName;
        this.fields = fields;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBuilderClassName() {
        return builderClassName;
    }

    public void setBuilderClassName(String builderClassName) {
        this.builderClassName = builderClassName;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "BuilderParam{" +
                "className='" + className + '\'' +
                ", builderClassName='" + builderClassName + '\'' +
                ", fields=" + fields +
                '}';
    }
}