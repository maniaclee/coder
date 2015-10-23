package psyco.coder.gen;

import psyco.coder.component.bean.Builder;
import psyco.coder.engine.BeetlEngine;
import psyco.coder.component.bean.BeanClass;

/**
 * Created by peng on 15/10/11.
 */
@Deprecated
public class CoderBuilder {
    public static BeanClass builder(String s, String pack) throws Exception {
        Builder b = (Builder) CoderJavabean.bean(s,Builder.class);
        b.setBuilderClassName(b.getClassName() + "Builder");
        b.setPack(pack);
        return b;
    }

    public static String exec(String s, String pack) throws Exception {
        return BeetlEngine.render("/template/builder.btl", "bp", builder(s, pack));
    }
}
