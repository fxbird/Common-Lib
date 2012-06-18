package com.xdg.util;

import com.xdg.bean.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLUtil {
    public static ResultSet query(Connection conn,String sql) throws SQLException {
        PreparedStatement pstmt=conn.prepareStatement(sql);
        return pstmt.executeQuery();
    }
}
