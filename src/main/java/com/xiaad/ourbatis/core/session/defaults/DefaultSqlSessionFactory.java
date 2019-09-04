package com.xiaad.ourbatis.core.session.defaults;

import com.xiaad.ourbatis.core.session.Configuration;
import com.xiaad.ourbatis.core.session.SqlSession;
import com.xiaad.ourbatis.core.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(configuration);
    }

    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }
}
