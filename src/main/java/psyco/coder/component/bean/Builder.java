package psyco.coder.component.bean;

import java.util.List;

/**
 * Created by peng on 15/10/24.
 */
public class Builder extends Class {
    public String builderClassName;

    public static Class newInstance(String s, String pack) throws Exception {
        Builder b = (Builder) Class.parseBeanExtended(s, Builder.class);
        b.setBuilderClassName(b.getClassName() + "Builder");
        b.setPack(pack);
        return b;
    }

    public Builder(String className, String pack, List<BeanField> fields) {
        super(className, fields);
    }

    public String getBuilderClassName() {
        return builderClassName;
    }

    public void setBuilderClassName(String builderClassName) {
        this.builderClassName = builderClassName;
    }
}
