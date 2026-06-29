/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quizapp_2451012015;

/**
 *
 * @author admin
 */
public class ThemeFactoryProducer {
    public static ThemeFactory getFactory(ThemeType type) {
        ThemeFactory fact;
        
        switch(type) {
            case DARK_THEME:
                fact = new DarkThemeFactory();
                break;
            case LIGHT_THEME:
                fact = new LightThemeFactory();
                break;
            default:
                fact = new DefaultThemeFactory();
        }
        return fact;
    }
}
