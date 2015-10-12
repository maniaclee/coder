package psyco.coder.gen;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import psyco.coder.db.jdbc.JDBCInfo;
import psyco.coder.db.jdbc.TableInfo;
import psyco.coder.db.jdbc.TableInfoBuilder;
import psyco.coder.engine.BeetlEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by peng on 15/10/11.
 */
public class CoderMybatis {
    static Logger logger = LoggerFactory.getLogger(CoderMybatis.class);

    public static String mapper(TableInfo beanClass, String mapperPackage) throws IOException {
        return BeetlEngine.render("/template/mybatis-mapper.btl", new ImmutableMap.Builder<String, Object>()
                .put("table", beanClass)
                .put("mapperPackage", mapperPackage)
                .build());
    }

    public static String xml(TableInfo beanClass) throws IOException {
        return BeetlEngine.render("/template/mybatis-mapper-xml.btl", new ImmutableMap.Builder<String, Object>()
                .put("table", beanClass)
                .build());
    }

    public static void mybatisProject(MybatisProjectConfig config) throws Exception {
        List<TableInfo> tables = TableInfoBuilder.fromJDBCInfo(config.getJdbcInfo());
        File entityDir = new File(config.entityDir);
        File mapperDir = new File(config.mapperDir);
        File xmlDir = new File(config.xmlDir);
        for (TableInfo tableInfo : tables) {
            try {
                File mapper = new File(mapperDir, tableInfo.getClassName() + "Mapper.java");
                File entity = new File(entityDir, tableInfo.getClassName() + ".java");
                File xml = new File(xmlDir, tableInfo.getClassName() + "Mapper.xml");
                if (mapper.exists() || entity.exists() || xml.exists()) {
                    logger.warn("Skip table:%s", tableInfo.getName());
                    continue;
                }
                IOUtils.write(mapper(tableInfo, config.mapperPackage), new FileWriter(mapper));
                IOUtils.write(CoderJdbcTableBean.exec(tableInfo, config.mapperPackage), new FileWriter(entity));
                IOUtils.write(xml(tableInfo), new FileWriter(xml));
                logger.info("finish project:%s",config);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static class MybatisProjectConfig {
        String entityPackage;
        String entityDir;
        String mapperPackage;
        String mapperDir;
        String xmlDir;
        JDBCInfo jdbcInfo;

        public JDBCInfo getJdbcInfo() {
            return jdbcInfo;
        }

        public void setJdbcInfo(JDBCInfo jdbcInfo) {
            this.jdbcInfo = jdbcInfo;
        }

        public String getEntityPackage() {
            return entityPackage;
        }

        public void setEntityPackage(String entityPackage) {
            this.entityPackage = entityPackage;
        }

        public String getEntityDir() {
            return entityDir;
        }

        public void setEntityDir(String entityDir) {
            this.entityDir = entityDir;
        }

        public String getMapperPackage() {
            return mapperPackage;
        }

        public void setMapperPackage(String mapperPackage) {
            this.mapperPackage = mapperPackage;
        }

        public String getMapperDir() {
            return mapperDir;
        }

        public void setMapperDir(String mapperDir) {
            this.mapperDir = mapperDir;
        }

        public String getXmlDir() {
            return xmlDir;
        }

        public void setXmlDir(String xmlDir) {
            this.xmlDir = xmlDir;
        }

        @Override
        public String toString() {
            return "MybatisProjectConfig{" +
                    "entityPackage='" + entityPackage + '\'' +
                    ", entityDir='" + entityDir + '\'' +
                    ", mapperPackage='" + mapperPackage + '\'' +
                    ", mapperDir='" + mapperDir + '\'' +
                    ", xmlDir='" + xmlDir + '\'' +
                    ", jdbcInfo=" + jdbcInfo +
                    '}';
        }
    }
}
