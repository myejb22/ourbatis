package com.xiaad.ourbatis.core.binding;

import com.xiaad.ourbatis.core.exception.BindingException;
import com.xiaad.ourbatis.core.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {

    private Map<Class<?>, MapperProxyFactory> knownMappers = new HashMap();

    public void addMapper(Class<?> type, Class<?> pojo) {
        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new BindingException("Type " + type + " is already known to the MapperRegistry.");
            }
            knownMappers.put(type, new MapperProxyFactory(type, pojo));
        }
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public <T> T getMapper(Class<?> type, SqlSession sqlSession){
        if(!knownMappers.containsKey(type)){
            throw new BindingException("Type " + type + " could not find");
        }

        MapperProxyFactory mapperProxyFactory = knownMappers.get(type);
        return (T)mapperProxyFactory.newProxyInstance(sqlSession);
    }
}
