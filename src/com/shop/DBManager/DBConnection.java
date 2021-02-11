/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DBManager;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Turab Bajeer
 */
public class DBConnection {
    
    private static Connection conn = null;
    
    public static Connection getConnection(){
    
        if(conn == null){
        
            try {
                
                Class.forName("com.mysql.jdbc.Driver");
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_management_system", "root", "root");
            } catch (Exception e) {
                System.out.println("ERROR: "+e.getMessage());
                e.printStackTrace();
            }
            
        }
        return conn;
    }
   
}
