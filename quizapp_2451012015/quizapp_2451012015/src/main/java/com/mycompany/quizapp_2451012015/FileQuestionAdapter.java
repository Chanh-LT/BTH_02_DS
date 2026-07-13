/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quizapp_2451012015;

import com.mycompany.quizapp_2451012015.model.Category;
import com.mycompany.quizapp_2451012015.model.Question;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class FileQuestionAdapter implements QuestionSource {
    private FileQuestionAdapter adaptee;
    private Category category;
    private Level level;

    public FileQuestionAdapter(FileQuestionAdapter adaptee, Category category, Level level) {
        this.adaptee = adaptee;
        this.category = category;
        this.level = level;
    }
    
    
    
    @Override
    public List<Question> loadQuestion() {
        try {
            return adaptee.parse(category, level);
        } catch (IOException ex) {
            Logger.getLogger(FileQuestionAdapter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }

    private List<Question> parse(Category category, Level level) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

