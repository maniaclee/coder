package psyco.coder.component.mybatis;

import com.google.common.base.Preconditions;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import psyco.coder.coder.CommonCoder;
import psyco.coder.component.bean.JavaBean;
import psyco.coder.component.jdbc.JdbcExecutor;
import psyco.coder.component.jdbc.TableInfo;
import psyco.coder.core.CoderProxy;

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
    private CommonCoder beanCoder = CoderProxy.load(CommonCoder.class);
    private IMybatis mybatisCoder = CoderProxy.load(IMybatis.class);

    private MybatisConfig config;

    public static CoderMybatis instance(MybatisConfig config) {
        CoderMybatis re = new CoderMybatis();
        re.config = config;
        return re;
    }


    private static String pack(String basePack, String pack) throws Exception {
        basePack = StringUtils.isBlank(basePack) ? "" : (basePack + ".");
        pack = basePack + pack;
        if (!pack.matches("\\S+(\\.\\S+)*"))
            throw new Exception("invalid package format:" + basePack + " and " + pack);
        return pack;
    }


    private static File package2file(File basePackageDir, String basePack, String pack) throws Exception {
        File re = new File(basePackageDir, pack(basePack, pack).replaceAll("\\.", "/"));
        if (!re.isDirectory())
            re.mkdirs();
        return re;
    }

    public void mybatisProject() throws Exception {
        Preconditions.checkArgument(config.pack != null, "Missing package");
        Preconditions.checkArgument(StringUtils.isNotBlank(config.pack.baseDir), "Missing base dir");

        JdbcExecutor jdbc = new JdbcExecutor(config.getJdbcInfo());
        jdbc.init();

        List<TableInfo> tables = jdbc.jdbcTables();
        MybatisPackageConfig pack = config.pack;
        File baseDir = new File(pack.baseDir);

        File entityDir = package2file(baseDir, pack.basePackage, pack.bean);
        File mapperDir = package2file(baseDir, pack.basePackage, pack.mapper);
        File dtoDir = package2file(baseDir, pack.basePackage, pack.dto);
        File dtoBuilderDir = package2file(baseDir, pack.basePackage, pack.builder);
        File xmlDir = new File(config.xmlDir);

        for (TableInfo tableInfo : tables) {
            try {
                /** package */
                tableInfo.setPack(pack(config.pack.basePackage, config.pack.bean));
                tableInfo.setAuthor(config.author);

                File mapper = new File(mapperDir, tableInfo.getClassName() + "Mapper.java");
                File entity = new File(entityDir, tableInfo.getClassName() + ".java");
                File xml = new File(xmlDir, tableInfo.getClassName() + "Mapper.xml");
                File dto = new File(dtoDir, tableInfo.getClassName() + "DTO.java");
                if (!config.isOverwrite() && (mapper.exists() || entity.exists() || xml.exists())) {
                    logger.warn("Skip table:%s", tableInfo.getName());
                    continue;
                }
                JavaBean bean = tableInfo.toBean();

                IOUtils.write(mybatisCoder.mapper(tableInfo, pack(config.pack.basePackage, config.pack.mapper)), new FileOutputStream(mapper));
                logger.info("write mapper:%s", mapper.getAbsolutePath());

                IOUtils.write(beanCoder.bean(bean), new FileOutputStream(entity));
                logger.info("write entity:%s", entity.getAbsolutePath());

                IOUtils.write(mybatisCoder.xml(
                                tableInfo,
                                pack(config.pack.basePackage, config.pack.mapper),
                                new IMybatis.SqlClauses(
                                        tableInfo.getColumns().stream().map(e -> e.getColumnName()).collect(Collectors.joining(",")),
                                        tableInfo.getColumns().stream().map(e -> String.format("#{%s}", e.getFieldName())).collect(Collectors.joining(",")))),
                        new FileOutputStream(xml));
                logger.info("write xml:%s", xml.getAbsolutePath());

                if (dtoDir != null && dtoDir.isDirectory()) {
                    JavaBean dtoBean = tableInfo.toBean();
                    dtoBean.setPack(pack(config.pack.basePackage, config.pack.dto));
                    dtoBean.setClassName(dtoBean.className + "DTO");
                    dtoBean.setClassNameLowerCase(dtoBean.classNameLowerCase + "DTO");

                    IOUtils.write(beanCoder.bean(dtoBean), new FileOutputStream(dto));
                    logger.info("write dto:%s", xml.getAbsolutePath());

                    if (dtoBuilderDir != null && dtoBuilderDir.isDirectory()) {
                        File dtoBuilderFile = new File(dtoBuilderDir, dtoBean.className + "Builder.java");

                        IOUtils.write(beanCoder.dtoBuilder(bean, dtoBean, pack(config.pack.basePackage, config.pack.builder)), new FileOutputStream(dtoBuilderFile));
                        logger.info("write dtoBuilder:%s", xml.getAbsolutePath());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.info("finish project:%s", config);
        }
    }


}
