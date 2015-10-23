package psyco.coder.core;

import com.google.common.collect.Maps;
import org.junit.Test;
import psyco.coder.coder.CommonCoder;
import psyco.coder.engine.BeetlEngine;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Optional;

/**
 * Created by peng on 15/10/24.
 */
public class CoderProxy {

    public static <T> T load(Class<T> tClass) {
        return (T) Proxy.newProxyInstance(CoderProxy.class.getClassLoader(), new Class[]{tClass}, new Handler());
    }

    private static class Handler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return Optional.ofNullable(method.getAnnotation(Template.class))
                    .map(temp -> temp.value())
                    .map(templatePath -> {
                        Parameter[] params = method.getParameters();
                        Map<String, Object> map = Maps.newHashMap();
                        for (int i = 0; i < params.length; i++)
                            map.put(getParameterName(params[i]), args[i]);
                        try {
                            return BeetlEngine.render(templatePath, map);
                        } catch (IOException e) {
                            e.printStackTrace();
                            throw new RuntimeException();
                        }
                    }).orElse("");
        }
    }

    private static String getParameterName(Parameter parameter) {
        return Optional.ofNullable(parameter.getAnnotation(Param.class))
                .map(param -> param.value())
                .orElse(parameter.getName());
    }

    @Test
    public void sdfsdf() {
        CommonCoder commonCoder = load(CommonCoder.class);
        System.out.println(commonCoder.bean("sdf", 3));
    }

}
