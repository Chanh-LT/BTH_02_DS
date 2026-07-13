/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quizapp_2451012015;

/**
 *
 * @author admin
 */
public class QuestionFacade {
    private final QuestionUpdateService dbService;
    
    public QuestionFacade() {
        this.dbService = new QuestionUpdateService();
    }
    
    public boolean importQuestion(QuestionSource source) {
        if(source == null)
            return false;
    }
    List<Question>
}
