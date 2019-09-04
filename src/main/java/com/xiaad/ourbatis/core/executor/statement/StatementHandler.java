package com.xiaad.ourbatis.core.executor.statement;

import com.xiaad.ourbatis.core.mapping.MappedStatement;

public interface StatementHandler {

    <T>T query(MappedStatement ms, Object[] args, Class<?> pojo);

    void update(MappedStatement ms, Object[] args);
}
