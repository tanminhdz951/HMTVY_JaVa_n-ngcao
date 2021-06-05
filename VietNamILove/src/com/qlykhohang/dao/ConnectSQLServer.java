/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlykhohang.dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ConnectSQLServer {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String hostName = "BAMBOO";
        String sqlInstanceName = "MSSQLSERVER01";
        String database = "QuanLyKhoHang";
        String userName = "sa";
        String password = "sa";
        return getConnection(hostName, sqlInstanceName, database, userName, password);
    }

    public static Connection getConnection(String hostName, String sqlInstanceName, String database, String userName, String password)
            throws ClassNotFoundException, SQLException {
        String connectionURL = "jdbc:sqlserver://" + hostName + ":1433"
                + ";instance=" + sqlInstanceName + ";databaseName=" + database;
        Connection con = DriverManager.getConnection(connectionURL, userName, password);
        return con;
    }
}
