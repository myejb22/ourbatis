package com.xiaad.ourbatis.core.executor;

import com.xiaad.ourbatis.core.mapping.MappedStatement;

public interface Executor {
    <T> T query(MappedStatement ms, Object[] parameter, Class pojo);

    void update(MappedStatement ms, Object[] parameter);
}
