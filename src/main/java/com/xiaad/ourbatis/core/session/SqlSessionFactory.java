package com.xiaad.ourbatis.core.session;

public interface SqlSessionFactory {

    SqlSession openSqlSession();

    Configuration getConfiguration();
}
