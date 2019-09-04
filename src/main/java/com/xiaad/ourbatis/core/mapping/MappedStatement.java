package com.xiaad.ourbatis.core.mapping;

import com.xiaad.ourbatis.core.session.Configuration;
import lombok.Data;

import java.util.Map;

@Data
public class MappedStatement {

    private Configuration configuration;
    private String id;
    //    private SqlSource sqlSource;
    private SqlCommandType sqlCommandType;
    private String sql;

    /**
     * 传入参数名称与参数顺序
     */
    private Map<String, Integer> parameterOrderMap;

    MappedStatement() {
        // constructor disabled
    }

    public static class Builder {
        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, String id, SqlSource sqlSource, SqlCommandType sqlCommandType) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
//            mappedStatement.sqlSource = sqlSource;
//            mappedStatement.sqlCommandType = sqlCommandType;
        }

        public Builder(Configuration configuration, String id, String sql, SqlCommandType sqlCommandType, Map<String, Integer> parameterOrderMap) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sql = sql;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.parameterOrderMap = parameterOrderMap;
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
//            assert mappedStatement.sqlSource != null;
//            assert  mappedStatement.sqlCommandType != null;
            assert mappedStatement.sql != null;
            return mappedStatement;
        }
    }
}
