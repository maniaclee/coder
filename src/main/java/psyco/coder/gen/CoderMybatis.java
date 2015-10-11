package psyco.coder.gen;

import com.google.common.collect.ImmutableMap;
import psyco.coder.db.jdbc.TableInfo;
import psyco.coder.engine.BeetlEngine;

import java.io.IOException;

/**
 * Created by peng on 15/10/11.
 */
public class CoderMybatis {

    public static class TableBean {
        TableInfo beanClass;
        String beanPackage;

    }

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
}
