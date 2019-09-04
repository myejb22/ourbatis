package com.xiaad.ourbatis.core.binding;

import com.xiaad.ourbatis.core.session.SqlSession;

import java.lang.reflect.Proxy;

public class MapperProxyFactory<T> {
    private Class<?> mapperInterface;
    private Class<?> pojo;

    public MapperProxyFactory(Class<?> mapperInterface, Class<?> pojo) {
        this.mapperInterface = mapperInterface;
        this.pojo = pojo;
    }

    public T newProxyInstance(SqlSession sqlSession) {
        MapperProxy mapperProxy = new MapperProxy(sqlSession, pojo);
        return newProxyInstance(mapperProxy);
    }

    protected T newProxyInstance(MapperProxy mapperProxy) {
        return (T) Proxy.newProxyInstance(
                mapperInterface.getClassLoader(),
                new Class[]{mapperInterface},
                mapperProxy);
    }
}
