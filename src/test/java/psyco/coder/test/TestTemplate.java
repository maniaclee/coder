package psyco.coder.test;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import psyco.coder.db.jdbc.JDBCInfo;
import psyco.coder.db.jdbc.TableInfo;
import psyco.coder.db.jdbc.TableInfoBuilder;
import psyco.coder.gen.CoderBuilder;
import psyco.coder.gen.CoderMybatis;

import java.io.FileReader;
import java.io.IOException;
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
        try {
            s = IOUtils.toString(new FileReader("/Users/peng/workspace/github/coder/src/main/java/psyco/coder/ast/parser/Shit.java"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sdfsd() throws Exception {
//        System.out.println(CoderJavabean.exec(s, ""));
        System.out.println(CoderBuilder.exec(s, ""));
    }

    @Test
    public void mybatis() throws Exception {
//        System.out.println(CoderJavabean.exec(s, ""));
        tableInfos().forEach(tableInfo -> {
            try {
                tableInfo.setPack("psyco.mybatis");
                System.out.println(CoderMybatis.xml(tableInfo));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void mybatisProject() throws Exception {
        CoderMybatis.MybatisProjectConfig config = new CoderMybatis.MybatisProjectConfig();
        config.setEntityDir("/Users/peng/workspace/github/coder-bot/coder-bot-core/src/main/java/psyco/codebot/dao/entity");
        config.setXmlDir("/Users/peng/workspace/github/coder-bot/coder-bot-core/src/main/java/psyco/codebot/dao/entity");
        CoderMybatis.mybatisProject(config);
    }
}
