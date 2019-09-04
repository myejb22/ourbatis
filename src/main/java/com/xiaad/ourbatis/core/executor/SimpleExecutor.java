package com.xiaad.ourbatis.core.executor;

import com.xiaad.ourbatis.core.executor.statement.SimpleStatementHandler;
import com.xiaad.ourbatis.core.executor.statement.StatementHandler;
import com.xiaad.ourbatis.core.mapping.MappedStatement;

public class SimpleExecutor implements Executor {

    @Override
    public void update(MappedStatement ms, Object[] parameter) {
        StatementHandler handler = new SimpleStatementHandler();
        handler.update(ms, parameter);
    }

    @Override
    public <T> T query(MappedStatement ms, Object[] parameter, Class pojo) {
        StatementHandler handler = new SimpleStatementHandler();

        return handler.query(ms, parameter, pojo);
    }


}
