package com.xiaad.ourbatis.core.session;

import com.xiaad.ourbatis.core.binding.MapperRegistry;
import com.xiaad.ourbatis.core.datasource.MyDataSource;
import com.xiaad.ourbatis.core.executor.Executor;
import com.xiaad.ourbatis.core.executor.SimpleExecutor;
import com.xiaad.ourbatis.core.mapping.MappedStatement;
import com.xiaad.ourbatis.core.mapping.SqlCommandType;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Configuration {
    //ourBatis全局的config
    private final static ResourceBundle ourBatisBundle;

    //mapper对象的解析
    private final static ResourceBundle mapperBundle;

    private MyDataSource dataSource;

    private MapperRegistry mapperRegistry = new MapperRegistry();

    private Map<String, MappedStatement> mapperStatement = new HashMap<>();

//    private Map<String, String> statementMap = new HashMap<>();


    static {
        ourBatisBundle = ResourceBundle.getBundle("ourbatis-config");
        mapperBundle = ResourceBundle.getBundle("mapper");
    }

    public Configuration() {
        //解析全局的ourbatis文件
        parse();
    }

    public <T> T getMapper(Class<?> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public MyDataSource getDataSource() {
        return dataSource;
    }

    public boolean hasStatement(String statement) {
        return mapperStatement.containsKey(statement);
    }

    public Executor getExecutor() {
        return new SimpleExecutor();
    }

    public MappedStatement getMapperStatement(String statement) {
        return mapperStatement.get(statement);
    }

    private void parse() {
        setDataSource();

        //解析mapper映射器
        mapperBundle.keySet().forEach(key -> {
            String statementId = key;
            String value = mapperBundle.getString(key);
            Class<?> statementObj = null;
            Class<?> pojoObj = null;
            String sql = null;

            try {
                statementObj = Class.forName(statementId.substring(0, statementId.lastIndexOf(".")));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (value.contains("--")) {
                String[] valueJoin = value.split("--");
                sql = valueJoin[0];
                String pojoStr = valueJoin[1];
                try {
                    pojoObj = Class.forName(pojoStr);
                    mapperRegistry.addMapper(statementObj, pojoObj);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                sql = value;
            }

            SqlCommandType commandType = null;
            Map<String, Integer> parameterOrderMap = null;
            if (sql.toUpperCase().contains(SqlCommandType.INSERT.name())) {
                commandType = SqlCommandType.INSERT;
                String parameterName = sql.substring(sql.indexOf("(") + 1, sql.lastIndexOf(") values"));
                String[] split = parameterName.split(",");
                parameterOrderMap = new HashMap<>(split.length);
                for (int i = 0; i < split.length; i++) {
                    String parameter = split[i];
                    if (parameter.contains("_")) {
                        for (int j = 0; j < parameter.length(); j++) {
                            if ((int) parameter.charAt(j) == 95) {
                                char newChar = Character.toUpperCase(parameter.charAt(j + 1));
                                parameter = parameter.replace(parameter.charAt(j + 1), newChar);
                                parameter = parameter.replace("_", "");
                                System.out.println(parameter);
                                break;
                            }
                        }
                    }
                    parameterOrderMap.put(parameter, i + 1);
                }


            } else if (sql.toUpperCase().contains(SqlCommandType.SELECT.name())) {
                commandType = SqlCommandType.SELECT;
            }

            MappedStatement.Builder builder = new MappedStatement.Builder(this, statementId, sql, commandType, parameterOrderMap);
            mapperStatement.put(statementId, builder.build());
            // statementMap.put(statementId, sql);
        });

        //扫描映射器包
        String mapperPath = ourBatisBundle.getString("mapper.path");
    }

    private void setDataSource() {
        String driver = ourBatisBundle.getString("jdbc.driver");
        String url = ourBatisBundle.getString("jdbc.url");
        String username = ourBatisBundle.getString("jdbc.username");
        String password = ourBatisBundle.getString("jdbc.password");
        dataSource = new MyDataSource(driver, url, username, password);
    }
}
