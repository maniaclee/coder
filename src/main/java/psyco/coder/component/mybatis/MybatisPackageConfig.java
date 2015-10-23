package psyco.coder.component.mybatis;

import java.io.Serializable;

/**
 * Created by peng on 15/10/23.
 */
public class MybatisPackageConfig implements Serializable {
    public String baseDir;
    public String basePackage;

    public String bean;
    public String dto;
    public String builder;
    public String mapper;
    public String repository;
    public String service;
    public String serviceImpl;

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
