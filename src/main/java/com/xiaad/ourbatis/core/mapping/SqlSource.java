package com.xiaad.ourbatis.core.mapping;

public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);
}
