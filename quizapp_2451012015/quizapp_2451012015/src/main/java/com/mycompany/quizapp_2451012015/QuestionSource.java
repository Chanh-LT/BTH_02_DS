/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.quizapp_2451012015;

import com.mycompany.quizapp_2451012015.model.Question;
import java.util.List;


/**
 *
 * @author admin
 */
public interface QuestionSource {
    List<Question> loadQuestion();
}
