package psyco.coder.gen;

import psyco.coder.component.bean.BeanField;
import psyco.coder.engine.BeetlEngine;
import psyco.coder.component.bean.BeanClass;

import java.util.List;

/**
 * Created by peng on 15/10/11.
 */
public class CoderBuilder {
    public static BeanClass builder(String s, String pack) throws Exception {
        BuilderParam b = (BuilderParam) CoderJavabean.bean(s,BuilderParam.class);
        b.setBuilderClassName(b.getClassName() + "Builder");
        b.setPack(pack);
        return b;
    }

    public static class BuilderParam extends BeanClass {
        public String builderClassName;

        public BuilderParam(String className, String pack, List<BeanField> fields) {
            super(className,  fields);
        }

        public String getBuilderClassName() {
            return builderClassName;
        }

        public void setBuilderClassName(String builderClassName) {
            this.builderClassName = builderClassName;
        }
    }

    public static String exec(String s, String pack) throws Exception {
        return BeetlEngine.render("/template/builder.btl", "bp", builder(s, pack));
    }
}
