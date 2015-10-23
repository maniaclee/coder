package psyco.coder.test;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import psyco.coder.component.jdbc.JdbcConfig;
import psyco.coder.component.mybatis.MybatisProjectCoder;
import psyco.coder.component.mybatis.MybatisConfig;
import psyco.coder.component.mybatis.MybatisPackageConfig;

import java.io.FileReader;

/**
 * Created by peng on 15/10/11.
 */
public class TestTemplate {

    @Test
    public void sdfsf() {
        Yaml y = new Yaml();
        MybatisPackageConfig pack = new MybatisPackageConfig();
        MybatisConfig config = new MybatisConfig();
        config.setAuthor("jdbc:mysql://localhost:3306/user?characterEncoding=UTF-8");
        config.setPack(pack);
        JdbcConfig jdbc = new JdbcConfig();
        jdbc.setUrl("jdbc:mysql://localhost:3306/user?characterEncoding=UTF-8");
        config.setJdbcInfo(jdbc);
        System.out.println(y.dump(config));
        System.out.println(y.loadAs(y.dump(config), MybatisConfig.class));
    }

    @Test
    public void newsds() throws Exception {
        Yaml y = new Yaml();
        MybatisConfig config = y.loadAs(new FileReader("/Users/peng/workspace/github/coder/src/main/resources/mybatis.gen.yaml"), MybatisConfig.class);
        MybatisProjectCoder.instance(config).mybatisProject();
    }

}
