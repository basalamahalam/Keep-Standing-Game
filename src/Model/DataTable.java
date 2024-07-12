/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ACER
 */

package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DataTable extends DB{
    
    public DataTable() throws Exception, SQLException {
        super();
    }
    
    public void getData() {
        try {
            // getting all data from table
            String query = "SELECT * FROM tbgame ORDER BY score DESC";
            createQuery(query);
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public void getDetailData(String username) {
        try {
            // getting specific data from table
            String query = "SELECT * FROM tbgame WHERE username='" + username + "'"; 
            createQuery(query);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void insertData(Data exp) {
        try {
            // input data to database
            String query = "INSERT INTO tbgame VALUES ('" + exp.getUsername() + "', '" + exp.getScore() + "', '" + exp.getStanding() + "')";
            createUpdate(query);
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public void updateData(Data exp) {
        try {
            // update data
            String query = "UPDATE tbgame SET score=" + exp.getScore() + ", standing=" + exp.getStanding() + " WHERE username='" + exp.getUsername() + "'";
            createUpdate(query);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
