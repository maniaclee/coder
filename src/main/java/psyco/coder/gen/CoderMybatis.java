package psyco.coder.gen;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import psyco.coder.db.jdbc.JDBCInfo;
import psyco.coder.db.jdbc.TableInfo;
import psyco.coder.db.jdbc.TableInfoBuilder;
import psyco.coder.engine.BeetlEngine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by peng on 15/10/11.
 */
public class CoderMybatis implements Serializable {
    static Logger logger = LoggerFactory.getLogger(CoderMybatis.class);


    private MybatisProjectConfig config;

    public static CoderMybatis instance(MybatisProjectConfig config) {
        CoderMybatis re = new CoderMybatis();
        re.config = config;
        return re;
    }

    public String mapper(TableInfo beanClass) throws IOException {
        return BeetlEngine.render("/template/mybatis-mapper.btl", new ImmutableMap.Builder<String, Object>()
                .put("table", beanClass)
                .put("config", config)
                .build());
    }

    public String entity(TableInfo beanClass) throws Exception {
        return CoderJdbcTableBean.exec(beanClass);
    }


    public String xml(TableInfo beanClass) throws IOException {
        return BeetlEngine.render("/template/mybatis-mapper-xml.btl", new ImmutableMap.Builder<String, Object>()
                .put("table", beanClass)
                .put("config", config)
                .put("selectClause", beanClass.getColumns().stream().map(e -> e.getColumnName()).collect(Collectors.joining(",")))
                .put("valuesClause", beanClass.getColumns().stream().map(e -> String.format("#{%s}", e.getFieldName())).collect(Collectors.joining(",")))
                .build());
    }


    public void mybatisProject(MybatisProjectConfig config) throws Exception {
        Preconditions.checkArgument(StringUtils.isNotBlank(config.getEntityPackage()), "Missing entity package");
        Preconditions.checkArgument(StringUtils.isNotBlank(config.getMapperPackage()), "Missing mapper package");

        List<TableInfo> tables = TableInfoBuilder.fromJDBCInfo(config.getJdbcInfo());
        File entityDir = new File(config.entityDir);
        File mapperDir = new File(config.mapperDir);
        File xmlDir = new File(config.xmlDir);
        for (TableInfo tableInfo : tables) {
            try {
                /** package */
                tableInfo.setPack(config.entityPackage);
                tableInfo.setAuthor(config.author);

                File mapper = new File(mapperDir, tableInfo.getClassName() + "Mapper.java");
                File entity = new File(entityDir, tableInfo.getClassName() + ".java");
                File xml = new File(xmlDir, tableInfo.getClassName() + "Mapper.xml");
                if (!config.isOverwrite() && (mapper.exists() || entity.exists() || xml.exists())) {
                    logger.warn("Skip table:%s", tableInfo.getName());
                    continue;
                }
                IOUtils.write(mapper(tableInfo), new FileOutputStream(mapper));
                logger.info("write mapper:%s", mapper.getAbsolutePath());
                IOUtils.write(CoderJdbcTableBean.exec(tableInfo), new FileOutputStream(entity));
                logger.info("write entity:%s", entity.getAbsolutePath());
                IOUtils.write(xml(tableInfo), new FileOutputStream(xml));
                logger.info("write xml:%s", xml.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.info("finish project:%s", config);
        }
    }


    public static class MybatisProjectConfig {
        String entityPackage;
        String entityDir;
        String mapperPackage;
        String mapperDir;
        String xmlDir;
        JDBCInfo jdbcInfo;
        String author;
        boolean overwrite = false;

        public boolean isOverwrite() {
            return overwrite;
        }

        public void setOverwrite(boolean overwrite) {
            this.overwrite = overwrite;
        }

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

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

    }
}
