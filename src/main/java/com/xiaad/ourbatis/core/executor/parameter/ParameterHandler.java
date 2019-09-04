package com.xiaad.ourbatis.core.executor.parameter;

import com.xiaad.ourbatis.core.exception.ParameterHandlerException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

public class ParameterHandler {
    private PreparedStatement preparedStatement;
    private Map<String, Integer> parameterOrderMap;

    public ParameterHandler(PreparedStatement preparedStatement, Map<String, Integer> parameterOrderMap) {
        this.preparedStatement = preparedStatement;
        this.parameterOrderMap = parameterOrderMap;
    }

    public void setParameterValues(Object[] parameterValues) {
        int k;

        for (int i = 0; i < parameterValues.length; i++) {
            k = i + 1;
            Object val = parameterValues[i];
            try {
                if (val instanceof Integer) {
                    preparedStatement.setInt(k, (Integer) val);
                } else if (val instanceof Long) {
                    preparedStatement.setLong(k, (Long) val);
                } else if (val instanceof String) {
                    preparedStatement.setString(k, String.valueOf(val));
                } else if (val instanceof Boolean) {
                    preparedStatement.setBoolean(k, (Boolean) val);
                } else if (val instanceof Object) {
                    Method[] methods = val.getClass().getDeclaredMethods();
                    
                    for (int j = 0; j < methods.length; j++) {
                        Method method = methods[j];
                        if (method.getName().contains("get") && Objects.nonNull(parameterOrderMap)) {
                            Class<?> returnType = method.getReturnType();
                            String methodName = method.getName();
                            String fieldName = getAttributeName(methodName);
                            try {
                                Field field = val.getClass().getDeclaredField(fieldName);
                                field.setAccessible(true);
                                if (returnType == Integer.class || returnType == int.class) {
                                    preparedStatement.setInt(parameterOrderMap.get(fieldName), (Integer) field.get(val));
                                } else if (returnType == String.class) {
                                    preparedStatement.setString(parameterOrderMap.get(fieldName), (String) field.get(val));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    preparedStatement.setString(k, String.valueOf(val));
                }
            } catch (SQLException e) {
                throw new ParameterHandlerException(this.getClass() + " parameter convert fail", e);
            }
        }
    }

    private String getAttributeName(String methodName) {
        String attributeName = methodName.substring(3);
        for (int i = 0; i < attributeName.length(); i++) {
            if (Character.isUpperCase(attributeName.charAt(i))) {
                char newChar = Character.toLowerCase(attributeName.charAt(i));
                attributeName = attributeName.replace(attributeName.charAt(i), newChar);
                break;
            }
        }
        return attributeName;
    }
}
