/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quizapp_2451012015;

/**
 *
 * @author admin
 */
public class DefaultThemeFactory implements ThemeFactory {

    @Override
    public String getBackgroundStyle() {
        return "-fx-background-color: #95D3DE; -fx-padding: 20";
    }

    @Override
    public String getButtonStyle() {
        return "-fx-font-size: 14px; -fx-text-fill: white; -fx-background-color: #2414FF; -fx-font-weight:bold ";
    }

    @Override
    public String getTitle() {
        return "-fx-font-size: 32px; -fx-font-weight:bold";
    }
    
}
