package psyco.coder.test;

import org.junit.Test;
import psyco.coder.db.jdbc.JDBCInfo;
import psyco.coder.db.jdbc.TableInfo;
import psyco.coder.db.jdbc.TableInfoBuilder;
import psyco.coder.gen.CoderBuilder;
import psyco.coder.gen.CoderMybatis;

import java.util.List;

/**
 * Created by peng on 15/10/11.
 */
public class TestTemplate {
    static String s;
    static JDBCInfo jdbc = new JDBCInfo("jdbc:mysql://localhost:3306/codebot?characterEncoding=UTF-8", "root", "");

    static List<TableInfo> tableInfos() throws Exception {
        return TableInfoBuilder.fromJDBCInfo(jdbc);
    }

    static {
//        try {
//            s = IOUtils.toString(new FileReader("/Users/peng/workspace/github/coder/src/main/java/psyco/coder/ast/parser/Shit.java"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void sdfsd() throws Exception {
//        System.out.println(CoderJavabean.exec(s, ""));
        System.out.println(CoderBuilder.exec(s, ""));
    }

//    @Test
//    public void mybatis() throws Exception {
////        System.out.println(CoderJavabean.exec(s, ""));
//        tableInfos().forEach(tableInfo -> {
//            try {
//                tableInfo.setPack("psyco.mybatis");
//                System.out.println(CoderMybatis.xml(tableInfo));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }

    @Test
    public void mybatisProject() throws Exception {
        CoderMybatis.MybatisProjectConfig config = new CoderMybatis.MybatisProjectConfig();
        config.setOverwrite(true);
        config.setAuthor("psyco");
        config.setJdbcInfo(jdbc);
        config.setEntityDir("/Users/peng/workspace/github/user-center/user-center-biz/src/main/java/psyco/user/center/dal/entity");
        config.setEntityPackage("psyco.user.center.dal.entity");

        config.setMapperPackage("psyco.user.center.dal.mapper");
        config.setMapperDir("/Users/peng/workspace/github/user-center/user-center-biz/src/main/java/psyco/user/center/dal/mapper");

        config.setXmlDir("/Users/peng/workspace/github/user-center/user-center-biz/src/main/resources/sqlmap");
//        CoderMybatis.instance(config).mybatisProject(config);

        System.out.println(CoderMybatis.instance(config).xml(tableInfos().get(0)));
//        System.out.println(CoderMybatis.instance(config).entity(tableInfos().get(0)));
    }
    @Test
    public void mybatisProject_home() throws Exception {
        CoderMybatis.MybatisProjectConfig config = new CoderMybatis.MybatisProjectConfig();
//        config.setOverwrite(true);
        config.setAuthor("psyco");
        config.setJdbcInfo(jdbc);
        config.setEntityDir("/Users/psyco/workspace/github/user-center/user-center-biz/src/main/java/psyco/user/center/dal/entity");
        config.setEntityPackage("psyco.user.center.dal.entity");

        config.setMapperPackage("psyco.user.center.dal.mapper");
        config.setMapperDir("/Users/psyco/workspace/github/user-center/user-center-biz/src/main/java/psyco/user/center/dal/mapper");

        config.setDtoDir("/Users/psyco/workspace/github/user-center/user-center-client/src/main/java/psyco/user/center/client/dto");
        config.setDtoPackage("psyco.user.center.client.dto");

        config.setDtoBuilderDir("/Users/psyco/workspace/github/user-center/user-center-biz/src/main/java/psyco/user/center/dal/convert");
        config.setDtoBuilderPackage("psyco.user.center.dal.convert");

        config.setXmlDir("/Users/psyco/workspace/github/user-center/user-center-biz/src/main/resources/sqlmap");
        CoderMybatis.instance(config).mybatisProject(config);

//        System.out.println(CoderMybatis.instance(config).xml(tableInfos().get(0)));
//        System.out.println(CoderMybatis.instance(config).entity(tableInfos().get(0)));
    }

}
