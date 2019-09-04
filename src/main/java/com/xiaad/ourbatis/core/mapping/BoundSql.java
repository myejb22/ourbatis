package com.xiaad.ourbatis.core.mapping;

import java.util.List;

public class BoundSql {
    private final String sql;
    private final List<Object> parameterMappings;
    private final Object parameterObject;

    public BoundSql(String sql,
                    List<Object> parameterMappings,
                    Object parameterObject) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.parameterObject = parameterObject;
    }
}
