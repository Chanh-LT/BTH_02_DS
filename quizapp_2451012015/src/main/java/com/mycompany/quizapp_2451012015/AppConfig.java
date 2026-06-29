/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quizapp_2451012015;

/**
 *
 * @author admin
 */
public class AppConfig {
    private static AppConfig instance;
    private int defaultQuestion = 5;
    private int defaultExamTime = 15;
    private boolean showAnswer = true;
    
    private AppConfig(){    
    }
    
    public static AppConfig getInstance() {
        if(instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    /**
     * @param aInstance the instance to set
     */
    public static void setInstance(AppConfig aInstance) {
        instance = aInstance;
    }

    /**
     * @return the defaultQuestion
     */
    public int getDefaultQuestion() {
        return defaultQuestion;
    }

    /**
     * @param defaultQuestion the defaultQuestion to set
     */
    public void setDefaultQuestion(int defaultQuestion) {
        this.defaultQuestion = defaultQuestion;
    }

    /**
     * @return the defaultExamTime
     */
    public int getDefaultExamTime() {
        return defaultExamTime;
    }

    /**
     * @param defaultExamTime the defaultExamTime to set
     */
    public void setDefaultExamTime(int defaultExamTime) {
        this.defaultExamTime = defaultExamTime;
    }

    /**
     * @return the showAnswer
     */
    public boolean isShowAnswer() {
        return showAnswer;
    }

    /**
     * @param showAnswer the showAnswer to set
     */
    public void setShowAnswer(boolean showAnswer) {
        this.showAnswer = showAnswer;
    }
}
    

