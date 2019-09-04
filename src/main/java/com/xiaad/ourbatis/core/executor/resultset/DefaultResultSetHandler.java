package com.xiaad.ourbatis.core.executor.resultset;

import com.xiaad.ourbatis.core.exception.ResultSetHandlerException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultResultSetHandler implements ResultSetHandler {
    @Override
    public <T> T resultSetProcessor(ResultSet rs, Class<?> pojoClass) {
        Object pojo = null;
        try {
            pojo = pojoClass.newInstance();

            if (rs.next()) {
                for (Field field : pojo.getClass().getDeclaredFields()) {
                    setValue(rs, pojo, field);
                }
            }

        } catch (Exception e) {
            throw new ResultSetHandlerException(this.getClass() + " processor fail:", e);
        }
        return (T) pojo;
    }

    private void setValue(ResultSet rs, Object pojo, Field field) throws Exception {
        Method method = pojo.getClass().getDeclaredMethod("set" + firstWordUpperCase(field.getName()),
                field.getType());

        method.invoke(pojo, getValue(rs, field));
    }

    private Object getValue(ResultSet rs, Field field) throws SQLException {
        Class<?> type = field.getType();
        String columnName = humpConvert(field.getName());
        if (Integer.class == type || int.class == type) {
            return rs.getInt(columnName);
        } else if (String.class == type) {
            return rs.getString(columnName);
        } else if (Long.class == type) {
            return rs.getLong(columnName);
        } else if (Boolean.class == type) {
            return rs.getBoolean(columnName);
        } else if (Double.class == type) {
            return rs.getDouble(columnName);
        } else {
            return rs.getString(columnName);
        }
    }

    /**
     * 驼峰转换
     *
     * @param parer
     * @return
     */
    private String humpConvert(String parer) {
        StringBuilder sb = new StringBuilder(parer);
        int temp = 0;

        if (!parer.contains("_")) {
            for (int i = 0; i < parer.length(); i++) {
                if (Character.isUpperCase(parer.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp++;
                }
            }
        }

        return sb.toString().toLowerCase();
    }

    /**
     * 设置首字母大写
     * @param name
     * @return
     */
    private String firstWordUpperCase(String name) {
        String first = name.substring(0, 1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }
}
