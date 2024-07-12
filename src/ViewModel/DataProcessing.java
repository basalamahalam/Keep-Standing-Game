/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ViewModel;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Model.DataTable;
import Model.Data;

/**
 *
 * @author ACER
 */
public class DataProcessing {
    private String error; // Variable to store error message
    private DataTable table; // Object for database operations 
    private ArrayList<Data> data; // List to store data
    
    // Constructor
    public DataProcessing() {
        try {
            // Initializing Table Object and List
            table = new DataTable();
            data = new ArrayList<Data>();
        } catch(Exception e) {
            error = e.toString();
        }
    }
    
    // Read Data From Database And Return It As DefaultTableModel
    public DefaultTableModel readData() {
        DefaultTableModel dataTbl = null;
        try {
            // Getting All Data From Experience Table
            Object[] column = {"username", "score", "standing"};
            dataTbl = new DefaultTableModel(null, column);
            table.getData();
            while (table.getResult().next()) {
                // Retrieve Query Result
                Data exp = new Data();
                exp.setUsername(table.getResult().getString(1));
                exp.setScore(table.getResult().getInt(2));
                exp.setStanding(table.getResult().getInt(3));
                
                Object[] row = new Object[3];
                row[0] = exp.getUsername();
                row[1] = exp.getScore();
                row[2] = exp.getStanding();
                
                // Add Data to List
                dataTbl.addRow(row);
                data.add(exp);
            }
            // Close Result
            table.closeResult();

            // Close Database Connection
            table.closeConnection();
        } catch(Exception e) {
            error = e.toString();
        }

        return dataTbl;
    }

    // Check if username exist in Database
    public boolean isDataExist(String username) {
        boolean result = false;
        try {
            table.getData();
            while (table.getResult().next()) {
                if (table.getResult().getString(1).equals(username)) {
                    result = true;
                    break;
                }
            }
        } catch (Exception e) {
            error = e.toString();
        }

        return result;
    }

    // Get Data
    public void getData(String username) {
        try {
            table.getDetailData(username);
            Data exp = new Data();
            table.getResult().next();
            exp.setUsername(table.getResult().getString(1));
            exp.setScore(table.getResult().getInt(2));
            exp.setStanding(table.getResult().getInt(3));

            data.add(exp);
            
            table.closeResult();
            table.closeConnection();
        } catch (Exception e) {
            error = e.toString();
        }
    }

    // Store Data
    public void storeData(String username, int score, int standing) {
        try {
            Data exp = new Data();
            exp.setUsername(username);
            exp.setScore(score);
            exp.setStanding(standing);
            
            // Check if username already exist in Database
            if (isDataExist(username)) {
                table.updateData(exp);
            } else {
                table.insertData(exp);
            }
            table.closeConnection();
        } catch (Exception e) {
            error = e.toString();
        }
    }

    // Get Data
    public String getUsername(int i) {
        return data.get(i).getUsername();
    }

    public int getScore(int i) {
        return data.get(i).getScore();
    }

    public int getStanding(int i) {
        return data.get(i).getStanding();
    }

    public int getSize() {
        return data.size();
    }

    public String getError() {
        return this.error;
    }
}
