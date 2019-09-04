package com.xiaad.ourbatis.core.session;

import com.xiaad.ourbatis.core.session.defaults.DefaultSqlSessionFactory;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory builder(){
        Configuration configuration = new Configuration();
        return new DefaultSqlSessionFactory(configuration);
    }
}
