/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quizapp_2451012015;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class JdbcConnector {
        private Connection connection;
        private static JdbcConnector instance;
        
        private JdbcConnector() {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:quizapp.db");
                System.out.println("Ket noi CSDL thanh cong");
            } catch (SQLException ex) {
                System.err.println("Loi ket noi" + ex.getMessage());
                Logger.getLogger(JdbcConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public static JdbcConnector getInstance() {
            if(instance == null) {
                instance = new JdbcConnector();
            }
            return instance;
        }
        
        public  Connection connect() {
            return connection;
        }
}
