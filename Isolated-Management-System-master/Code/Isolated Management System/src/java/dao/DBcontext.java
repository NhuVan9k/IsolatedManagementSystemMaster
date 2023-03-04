/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import utils.Configs;

public class DBcontext {
    private static Connection connect;//acc
    
    public static Connection getConnection() {
        if (connect != null) return connect;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connect = (Connection) DriverManager.getConnection("jdbc:sqlserver://"+Configs.SERVER+":"+Configs.PORT_NUMBER +";databaseName="+Configs.DB_NAME ,
                    Configs.DB_USERNAME, Configs.DB_PASSWORD);
            System.out.println("Connect databse successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connect;
    }
    
    public static void main(String[] args) {
        DBcontext.getConnection();
    }
}