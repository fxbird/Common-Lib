package com.xdg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class ConnFactory {
    public static Connection getConnection(String type, String url, String userName, String password) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        HashMap<String, String> types = new HashMap<String, String>();
        types.put("oracle", "oracle.jdbc.driver.OracleDriver");
        types.put("mysql", "com.mysql.jdbc.Driver");
        types.put("mssql", "net.sourceforge.jtds.jdbc.Driver");

        Class.forName(types.get(type)).newInstance();

        Connection conn = DriverManager.getConnection(url, userName, password);

        return conn;
    }
}
