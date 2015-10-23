package psyco.coder.coder;

import psyco.coder.component.bean.BeanClass;
import psyco.coder.component.bean.Builder;
import psyco.coder.core.Param;
import psyco.coder.core.Template;

/**
 * Created by peng on 15/10/24.
 */
public interface CommonCoder {

    @Template("template/bean.btl")
    String bean(@Param("bean") BeanClass beanClass);

    @Template("/template/builder.btl")
    String builder(@Param("bp") Builder builder);

    @Template("template/dto-builder.btl")
    String exec(@Param("bean") BeanClass bean, @Param("dto") BeanClass dto, @Param("dtoBuilderPack") String dtoBuilderPack);
}
