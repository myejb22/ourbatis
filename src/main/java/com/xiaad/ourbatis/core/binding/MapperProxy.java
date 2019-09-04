package com.xiaad.ourbatis.core.binding;

import com.xiaad.ourbatis.core.mapping.MappedStatement;
import com.xiaad.ourbatis.core.mapping.SqlCommandType;
import com.xiaad.ourbatis.core.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy implements InvocationHandler {
    private Class<?> pojo;
    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession, Class pojo) {
        this.sqlSession = sqlSession;
        this.pojo = pojo;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mapperInterface = method.getDeclaringClass().getName();
        String mapperMethodName = method.getName();
        String statementId = mapperInterface + "." + mapperMethodName;

        if (sqlSession.getConfiguration().hasStatement(statementId)) {
            MappedStatement ms = sqlSession.getConfiguration().getMapperStatement(statementId);
            if (ms.getSqlCommandType() == SqlCommandType.SELECT) {
                return sqlSession.selectOne(statementId, args, pojo);
            } else if (ms.getSqlCommandType() == SqlCommandType.INSERT) {
                sqlSession.update(statementId, args);
            }
        }
        return null;
    }
}
