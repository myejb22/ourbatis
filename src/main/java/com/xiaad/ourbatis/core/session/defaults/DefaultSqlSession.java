package com.xiaad.ourbatis.core.session.defaults;

import com.xiaad.ourbatis.core.executor.Executor;
import com.xiaad.ourbatis.core.mapping.MappedStatement;
import com.xiaad.ourbatis.core.session.Configuration;
import com.xiaad.ourbatis.core.session.SqlSession;

public class DefaultSqlSession implements SqlSession {
    private final Configuration configuration;
    private final Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = configuration.getExecutor();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public <T> T getMapper(Class<?> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public void update(String statement, Object[] args) {
        executor.update(configuration.getMapperStatement(statement), args);
    }

    @Override
    public <T> T selectOne(String statement, Object[] args, Class<?> pojo) {
        MappedStatement mappedStatement = configuration.getMapperStatement(statement);

        return executor.query(mappedStatement, args, pojo);
    }
}
