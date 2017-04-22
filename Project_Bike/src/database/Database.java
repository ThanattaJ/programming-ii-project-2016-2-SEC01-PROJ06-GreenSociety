/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bike.database;

import java.sql.*;
public class Database {
    public static Connection connectDb(String user,String pass) {
        String url = "jdbc:mysql://10.4.53.34:3306/Green_Society";
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url,user,pass);
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}