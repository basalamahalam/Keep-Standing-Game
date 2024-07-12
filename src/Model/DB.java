/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ACER
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    // config for mysql connection
    private String url = "jdbc:mysql://localhost:3306/db_game?user=root&password=";
    private Statement stm = null;   // query connection
    private ResultSet rs = null;    // result
    private Connection conn = null; // mysql connection
    
    // Constructor
    public DB() throws Exception, SQLException {
        try {
            // creating driver mysql
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // creating mysql connection
            conn = DriverManager.getConnection(url);
            conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);
        } catch(SQLException e) {
            // throw error when connection failed
            e.printStackTrace();
        }
    }
    
    public void createQuery(String query) throws Exception, SQLException {
        // executing query without manipulating data
        try {
            stm = conn.createStatement();
            // query execution
            rs = stm.executeQuery(query);
            if (stm.execute(query)) {
                // get result
                rs = stm.getResultSet();
            }
        } catch(SQLException e) {
            // throw error when query execution failed
            throw e;
        }
    }
    
    public void createUpdate(String query) throws Exception, SQLException {
        // executing query for manipulating data
        try {
            stm = conn.createStatement();
            // query execution
            int result = stm.executeUpdate(query);
        } catch(SQLException e) {
            // throw error when query execution failed
            throw e;
        }
    }
    
    public ResultSet getResult() throws Exception {
        ResultSet temp = null;
        try {
            return rs;
        } catch(Exception e) {
            return temp;
        }
    }
    
    public void closeResult() throws Exception, SQLException {
        if (rs != null) {
            try {
                rs.close();
            } catch(SQLException e) {
                rs = null;
                throw e;
            }
        }
        
        if (stm != null) {
            try {
                stm.close();
            } catch(SQLException e) {
                stm = null;
                throw e;
            }
        }
    }
    
    public void closeConnection() throws Exception, SQLException {
        if (conn != null) {
            try {
                conn.close();
            } catch(SQLException e) {
                conn = null;
                throw e;
            }
        }
    }
}
