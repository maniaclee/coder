package psyco.coder.gen;

import com.google.common.collect.ImmutableMap;
import psyco.coder.component.bean.BeanClass;
import psyco.coder.engine.BeetlEngine;

import java.io.IOException;

/**
 * Created by lipeng on 15/10/15.
 */
public class CoderDTOBuilder {

    public static String exec(BeanClass bean, BeanClass dto, String dtoBuilderPack) throws IOException {
        return BeetlEngine.render("/template/dto-builder.btl", new ImmutableMap.Builder()
                .put("bean", bean)
                .put("dto", dto)
                .put("dtoBuilderPack", dtoBuilderPack)
                .build());
    }
}
