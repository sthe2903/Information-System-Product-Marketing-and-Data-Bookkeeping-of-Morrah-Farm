/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekpbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mahes
 */
public class dbConnection {
    private static final Logger logger = Logger.getLogger(dbConnection.class.getName());
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost/morrah_farm_projectPBO";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    
    private dbConnection(){
        
    }
    
    public static Connection getDBConnection() throws SQLException {
        Connection connection = null;
        
        try{
            Class.forName(DB_DRIVER);
        }catch(ClassNotFoundException exception){
            logger.log(Level.SEVERE, exception.getMessage());
        }
        
        try{
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            System.out.println("Koneksi Berhasil"); 
            return connection;
        }catch(SQLException exception){
            logger.log(Level.SEVERE, exception.getMessage());
        }
        
        return connection;
    }
}
