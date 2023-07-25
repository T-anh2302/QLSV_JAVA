/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.connect;

/**
 *
 * @author vtuan
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vtuan
 */
public class MyConnection {
    
    private String user = "root";
    private String password = "";
    private String host = "localhost";
    private String port = "3306";
    private String dbName = "l02_java_test";
    private String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName +"?useUnicode=yes&characterEncoding=UTF-8";
    private Connection conn = null;
    public Connection getInstance() {
        if ( conn == null) {
            conn = openConnecttion();
        }
        return conn;
    }
    public Connection openConnecttion()  {
        
        try {
           conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public void closeConnection() {
        if ( conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        MyConnection myConnection = new MyConnection();
        Connection conn = myConnection.getInstance();
        if (conn != null) {
            System.out.println("Success!");
        } else {
            System.out.println("not success!");
        }
    }
}

