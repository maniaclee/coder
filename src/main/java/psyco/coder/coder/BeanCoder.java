package psyco.coder.coder;

import psyco.coder.component.bean.JavaBean;
import psyco.coder.component.bean.Builder;
import psyco.coder.core.Param;
import psyco.coder.core.Template;

/**
 * Created by peng on 15/10/24.
 */
public interface BeanCoder {

    @Template("/template/bean.btl")
    String bean(@Param("bean") JavaBean beanClass);

    @Template("/template/builder.btl")
    String builder(@Param("bp") Builder builder);

    @Template("/template/dto-builder.btl")
    String dtoBuilder(@Param("bean") JavaBean bean, @Param("dto") JavaBean dto, @Param("dtoBuilderPack") String dtoBuilderPack);
}
