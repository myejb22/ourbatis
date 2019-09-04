package com.xiaad.ourbatis.core.session;

public interface SqlSession {

    <T> T getMapper(Class<?> clazz);

    <T> T selectOne(String statement, Object[] args, Class<?> pojo);

    void update(String statement, Object[] args);

    default Configuration getConfiguration() {
        return getConfiguration();
    }
}
