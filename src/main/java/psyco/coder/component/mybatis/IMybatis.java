package psyco.coder.component.mybatis;

import psyco.coder.component.jdbc.TableInfo;
import psyco.coder.core.Param;
import psyco.coder.core.Template;

/**
 * Created by peng on 15/10/24.
 */
public interface IMybatis {

    @Template("/template/mybatis-mapper.btl")
    String mapper(@Param("table") TableInfo beanClass, @Param("mapperPackage") String mapperPackage);

    @Template("/template/mybatis-xml.btl")
    String xml(@Param("table") TableInfo beanClass, @Param("mapperPackage") String mapperPackage, @Param("sqls") SqlClauses sqlClauses);

    class SqlClauses {
        String selectClause;
        String valuesClause;

        public SqlClauses(String selectClause, String valuesClause) {
            this.selectClause = selectClause;
            this.valuesClause = valuesClause;
        }

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
