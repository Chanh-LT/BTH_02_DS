/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quizapp_2451012015;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author admin
 */
public class MyAlert {
    private static MyAlert instance;
    private Alert alert;
    
    private  MyAlert() {
        alert = new Alert(Alert.AlertType.INFORMATION);
    }
    
    public static MyAlert getInstance() {
        if(instance == null) {
            instance = new MyAlert();
        }
        return instance;
    }
    
    public Optional<ButtonType> showMessages(String message) {
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle("Quiz app");
        alert.setHeaderText("Annoucement");
        alert.setContentText(message);
        
        return alert.showAndWait();
    }

    void showError(String please_nhập_câu_hỏi_và_gợi_ý) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
