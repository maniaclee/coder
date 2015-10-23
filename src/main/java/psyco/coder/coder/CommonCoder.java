package psyco.coder.coder;

import psyco.coder.component.bean.Class;
import psyco.coder.component.bean.Builder;
import psyco.coder.core.Param;
import psyco.coder.core.Template;

/**
 * Created by peng on 15/10/24.
 */
public interface CommonCoder {

    @Template("/template/bean.btl")
    String bean(@Param("bean") Class beanClass);

    @Template("/template/builder.btl")
    String builder(@Param("bp") Builder builder);

    @Template("/template/dto-builder.btl")
    String dtoBuilder(@Param("bean") Class bean, @Param("dto") Class dto, @Param("dtoBuilderPack") String dtoBuilderPack);
}
