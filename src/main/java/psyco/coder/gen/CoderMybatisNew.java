package psyco.coder.gen;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import psyco.coder.bean.BeanClass;
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
public class CoderMybatisNew implements Serializable {
    static Logger logger = LoggerFactory.getLogger(CoderMybatisNew.class);


    private MybatisTemplateConfig config;

    public static CoderMybatisNew instance(MybatisTemplateConfig config) {
        CoderMybatisNew re = new CoderMybatisNew();
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

    private static String package2path(String pack) {
        return pack.trim().replaceAll(".", "/");
    }

    private static File package2file(File base, String pack) throws Exception {
        File re = new File(base, package2path(pack));
        if (!re.isDirectory() && !re.mkdirs())
            throw new Exception("error parsing dir with package:\t" + pack);
        return re;
    }

    private static File package2file(File base, String basePack, String pack) throws Exception {
        if (StringUtils.isBlank(pack))
            return null;
        pack = pack.startsWith(basePack) ? (basePack + "." + pack) : pack;
        if (!pack.matches("\\S+(\\.\\S+)*"))
            throw new Exception("invalid package format:" + basePack + " and " + pack);
        return package2file(base, pack.startsWith(basePack) ? (basePack + "." + pack) : pack);
    }

    public void mybatisProject() throws Exception {
        Preconditions.checkArgument(config.pack != null, "Missing package");
        Preconditions.checkArgument(StringUtils.isNotBlank(config.pack.baseDir), "Missing base dir");

        List<TableInfo> tables = TableInfoBuilder.fromJDBCInfo(config.jdbcInfo);
        MybatisTemplateConfig_Package pack = config.pack;
        File baseDir = new File(pack.baseDir);

        File entityDir = package2file(baseDir, pack.basePackage, pack.bean);
        File mapperDir = package2file(baseDir, pack.basePackage, pack.mapper);
        File dtoDir = package2file(baseDir, pack.basePackage, pack.dto);
        File dtoBuilderDir = package2file(baseDir, pack.basePackage, pack.builder);
        File xmlDir = new File(config.xmlDir);

        for (TableInfo tableInfo : tables) {
            try {
                /** package */
                tableInfo.setPack(config.pack.bean);
                tableInfo.setAuthor(config.author);

                File mapper = new File(mapperDir, tableInfo.getClassName() + "Mapper.java");
                File entity = new File(entityDir, tableInfo.getClassName() + ".java");
                File xml = new File(xmlDir, tableInfo.getClassName() + "Mapper.xml");
                File dto = new File(dtoDir, tableInfo.getClassName() + "DTO.java");
                if (!config.isOverwrite() && (mapper.exists() || entity.exists() || xml.exists())) {
                    logger.warn("Skip table:%s", tableInfo.getName());
                    continue;
                }
                BeanClass bean = CoderJdbcTableBean.tableInfo(tableInfo);

                IOUtils.write(mapper(tableInfo), new FileOutputStream(mapper));
                logger.info("write mapper:%s", mapper.getAbsolutePath());

                IOUtils.write(CoderJavabean.exec(bean), new FileOutputStream(entity));
                logger.info("write entity:%s", entity.getAbsolutePath());

                IOUtils.write(xml(tableInfo), new FileOutputStream(xml));
                logger.info("write xml:%s", xml.getAbsolutePath());

                if (dtoDir != null && dtoDir.isDirectory()) {
                    BeanClass dtoBean = CoderJdbcTableBean.tableInfo(tableInfo);
                    dtoBean.setPack(config.pack.dto);
                    dtoBean.setClassName(dtoBean.className + "DTO");
                    dtoBean.setClassNameLowerCase(dtoBean.classNameLowerCase + "DTO");

                    IOUtils.write(CoderJavabean.exec(dtoBean), new FileOutputStream(dto));
                    logger.info("write dto:%s", xml.getAbsolutePath());

                    if (dtoBuilderDir != null && dtoBuilderDir.isDirectory()) {
                        File dtoBuilderFile = new File(dtoBuilderDir, dtoBean.className + "Builder.java");

                        IOUtils.write(CoderDTOBuilder.exec(bean, dtoBean, config.pack.dto), new FileOutputStream(dtoBuilderFile));
                        logger.info("write dtoBuilder:%s", xml.getAbsolutePath());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.info("finish project:%s", config);
        }
    }


    public static class MybatisTemplateConfig {
        String xmlDir;
        JDBCInfo jdbcInfo;
        String author;
        boolean overwrite = false;
        MybatisTemplateConfig_Package pack;

        public String getXmlDir() {
            return xmlDir;
        }

        public void setXmlDir(String xmlDir) {
            this.xmlDir = xmlDir;
        }

        public JDBCInfo getJdbcInfo() {
            return jdbcInfo;
        }

        public void setJdbcInfo(JDBCInfo jdbcInfo) {
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

        public MybatisTemplateConfig_Package getPack() {
            return pack;
        }

        public void setPack(MybatisTemplateConfig_Package pack) {
            this.pack = pack;
        }
    }

    public static class MybatisTemplateConfig_Package implements Serializable {
        String baseDir;
        String basePackage;

        String bean;
        String dto;
        String builder;
        String mapper;
        String repository;
        String service;
        String serviceImpl;

        public String getBuilder() {
            return builder;
        }

        public void setBuilder(String builder) {
            this.builder = builder;
        }

        public String getBaseDir() {
            return baseDir;
        }

        public void setBaseDir(String baseDir) {
            this.baseDir = baseDir;
        }

        public String getBasePackage() {
            return basePackage;
        }

        public void setBasePackage(String basePackage) {
            this.basePackage = basePackage;
        }

        public String getDto() {
            return dto;
        }

        public void setDto(String dto) {
            this.dto = dto;
        }

        public String getBean() {
            return bean;
        }

        public void setBean(String bean) {
            this.bean = bean;
        }

        public String getMapper() {
            return mapper;
        }

        public void setMapper(String mapper) {
            this.mapper = mapper;
        }

        public String getRepository() {
            return repository;
        }

        public void setRepository(String repository) {
            this.repository = repository;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getServiceImpl() {
            return serviceImpl;
        }

        public void setServiceImpl(String serviceImpl) {
            this.serviceImpl = serviceImpl;
        }
    }


}
