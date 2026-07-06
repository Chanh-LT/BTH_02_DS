/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quizapp_2451012015;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class QuestionRepository {
    private QuestionQueryBuilder queryBuilder = new QuestionQueryBuilder();
    public List<Question> getQuestionByFilter(String keyword, int category_id, int level_id, int limit) {
        List<Question> questions = new ArrayList<>();
        try {
            
            QuestionQueryBuilder queryBuilder = new QuestionQueryBuilder().categoryId(category_id).keyword(keyword).limit(limit).levelId(level_id);
            Connection connection = JdbcConnector.getInstance().connect();
            PreparedStatement stmt = connection.prepareStatement(queryBuilder.build());
            
            int idx = 1;
            for(Object param : queryBuilder.getParams()) {
                stmt.setObject(idx, param);
                idx++;
            }
            
            ResultSet rs = stmt.executeQuery();
            List<Question> tmp = new ArrayList<>();
            int questionId = -1;
            while(rs.next()) {
                questionId = rs.getInt("id");
                Question q = new Question(rs.getString("content"), rs.getString("hint"), rs.getInt("level"), rs.getInt("category"), "", getChoices(questionId));
                tmp.add(q);
            }
            
            for (Question q : tmp) {
                q.setChoices(getChoices(questionId));
                questions.add(q);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }

    private List<Choice> getChoices(int questionId) {
        List<Choice> choices = new ArrayList<>();
        try(Connection conn = JdbcConnector.getInstance().connect()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, content, is_correct " + " FROM choice WHERE question_id = ?");
            stmt.setInt(1, questionId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Choice choice = new Choice(rs.getInt("id"), rs.getString("content"),
                        rs.getInt("is_correct") == 1);
                choices.add(choice);
            }
//            stmt.close();
//            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return choices;
    }
}
            
            
//        QuestionQueryBuilder queryBuilder = new QuestionQueryBuilder().categoryId(category_id).keyword(keyword).limit(limit).levelId(level_id);
//        Connection connection = JdbcConnector.getInstance().connect();
//        PreparedStatement stmt = connection.prepareStatement(queryBuilder.build());
//        return questions;

