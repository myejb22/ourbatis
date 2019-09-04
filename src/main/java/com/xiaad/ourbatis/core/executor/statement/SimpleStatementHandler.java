package com.xiaad.ourbatis.core.executor.statement;

import com.xiaad.ourbatis.core.datasource.MyDataSource;
import com.xiaad.ourbatis.core.exception.OurBatisConnectionException;
import com.xiaad.ourbatis.core.exception.StatementHandlerException;
import com.xiaad.ourbatis.core.executor.parameter.ParameterHandler;
import com.xiaad.ourbatis.core.executor.resultset.DefaultResultSetHandler;
import com.xiaad.ourbatis.core.executor.resultset.ResultSetHandler;
import com.xiaad.ourbatis.core.mapping.MappedStatement;

import java.sql.*;
import java.util.Objects;

public class SimpleStatementHandler implements StatementHandler {
    private ResultSetHandler resultSetHandler = new DefaultResultSetHandler();

    @Override
    public void update(MappedStatement ms, Object[] args) {
        Connection connection = getConnection(ms.getConfiguration().getDataSource());
        PreparedStatement ps = null;

        ps = getPreparedStatement(connection, ms.getSql());
        ParameterHandler parameterHandler = new ParameterHandler(ps, ms.getParameterOrderMap());
        parameterHandler.setParameterValues(args);

        try {
            int cnt = ps.executeUpdate();
            System.out.println("====>" + cnt);
        } catch (SQLException e) {
            throw new StatementHandlerException(this.getClass() + " update error", e);
        } finally {
            close(connection, ps);
        }
    }

    @Override
    public <T> T query(MappedStatement ms, Object[] args, Class<?> pojo) {
        Connection connection = getConnection(ms.getConfiguration().getDataSource());
        PreparedStatement ps = null;

        try {

            ps = getPreparedStatement(connection, ms.getSql());
            ParameterHandler parameterHandler = new ParameterHandler(ps, ms.getParameterOrderMap());
            parameterHandler.setParameterValues(args);
            ps.execute();

            return resultSetHandler.resultSetProcessor(ps.getResultSet(), pojo);
        } catch (Exception e) {
            throw new StatementHandlerException(this.getClass() + " executor error", e);
        } finally {
            close(connection, ps);
        }
    }

    private PreparedStatement getPreparedStatement(Connection connection, String sql) {
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new StatementHandlerException(this.getClass() + " query was error", e);
        }
    }

    private Connection getConnection(MyDataSource dataSource) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dataSource.getUrl(),
                    dataSource.getUsername(),
                    dataSource.getPassword());
        } catch (SQLException e) {
            throw new OurBatisConnectionException(this.getClass() + " connection was fail");
        }
        return conn;
    }

    private void close(Connection connection, PreparedStatement preparedStatement) {
        try {
            if (Objects.nonNull(preparedStatement)) {
                preparedStatement.close();
            }

            if (Objects.nonNull(connection)) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new StatementHandlerException(this.getClass() + " close error", e);
        }
    }
}
