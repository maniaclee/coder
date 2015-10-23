package psyco.coder.component.mybatis;

import psyco.coder.component.jdbc.JdbcConfig;

/**
 * Created by peng on 15/10/23.
 */
public class MybatisConfig {
    public String xmlDir;
    public JdbcConfig jdbcInfo;
    public String author;
    public boolean overwrite = false;
    public MybatisPackageConfig pack;

    public String getXmlDir() {
        return xmlDir;
    }

    public void setXmlDir(String xmlDir) {
        this.xmlDir = xmlDir;
    }

    public JdbcConfig getJdbcInfo() {
        return jdbcInfo;
    }

    public void setJdbcInfo(JdbcConfig jdbcInfo) {
        this.jdbcInfo = jdbcInfo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public MybatisPackageConfig getPack() {
        return pack;
    }

    public void setPack(MybatisPackageConfig pack) {
        this.pack = pack;
    }
}
