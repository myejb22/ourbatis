package com.xiaad.ourbatis.core.executor.resultset;

import java.sql.ResultSet;

public interface ResultSetHandler {

    <T> T resultSetProcessor(ResultSet rs, Class<?> pojo);
}
