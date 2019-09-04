package com.xiaad.ourbatis.core.datasource;

import lombok.Data;

@Data
public class MyDataSource {

    private String driver;

    private String url;

    private String username;

    private String password;

    public MyDataSource(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
}
