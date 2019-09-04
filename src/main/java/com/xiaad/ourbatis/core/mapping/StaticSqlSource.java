package com.xiaad.ourbatis.core.mapping;

import java.util.List;

public class StaticSqlSource implements SqlSource {
    private final String sql;
    private final List<Object> parameterMappings;

    public StaticSqlSource(String sql, List<Object> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return new BoundSql(sql,parameterMappings,parameterObject);
    }
}
