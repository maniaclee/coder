package psyco.coder.component.mybatis;

import psyco.coder.ast.annotation.Template;
import psyco.coder.component.jdbc.TableInfo;
import psyco.coder.core.Param;

/**
 * Created by peng on 15/10/24.
 */
public interface IMybatis {

    @Template("/template/mapper-mybatis.btl")
    String mapper(@Param("table") TableInfo beanClass, @Param("mapperPackage") String mapperPackage);

    @Template("/template/mapper-xml-mybatis.btl")
    String xml(@Param("table") TableInfo beanClass, @Param("mapperPackage") String mapperPackage, SqlClauses sqlClauses);

    class SqlClauses {
        String selectClause;
        String valuesClause;

        public String getSelectClause() {
            return selectClause;
        }

        public void setSelectClause(String selectClause) {
            this.selectClause = selectClause;
        }

        public String getValuesClause() {
            return valuesClause;
        }

        public void setValuesClause(String valuesClause) {
            this.valuesClause = valuesClause;
        }
    }
}
