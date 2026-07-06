/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quizapp_2451012015;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuestionQueryBuilder {
    private Integer categoryId;
    private Integer levelId;
    private String keyword;
    
    private int limit = 5;
    
    private List<Object> params;

    public QuestionQueryBuilder(Integer categoryId) {
        this.params = new ArrayList();
        this.categoryId = categoryId;
        this.levelId = levelId;
    }
    
    public QuestionQueryBuilder(){}

    public QuestionQueryBuilder(Integer categoryId, Integer levelId) {
        this.params = new ArrayList();
        this.categoryId = categoryId;
        this.levelId = levelId;
    }
    
    public String build() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ");
        builder.append(" FROM question q ");
        builder.append(" JOIN category c ON c.id = q.category_Id");
        builder.append(" JOIN level l ON l.id = q.level_id ");
        builder.append(" WHERE 1 = 1 ");
        
        if(keyword != null) {
            builder.append(" AND q.content LIKE ?");
            getParams().add("%" + keyword.trim() + "%");            
        }
        
        if(categoryId != null) {
            builder.append(" AND q.id = ?");
            getParams().add(categoryId);
        }
        
        if(levelId != null) {
            builder.append(" AND l.id = ?");
            getParams().add(levelId);
        }
        builder.append("ORDER BY RANDOM() LIMIT");
        getParams().add(limit);
        return builder.toString();
    }

    public List<Object> getParams() {
        return params;
    }
    
    public QuestionQueryBuilder levelId(int level_id) {
        this.levelId = level_id;
        return this;
    }
    
    public QuestionQueryBuilder limit(int limit) {
        this.limit = limit;
        return this;
    }
    
    public QuestionQueryBuilder categoryId(int category_id) {
        this.categoryId = category_id;
        return this;
    }
    
    public QuestionQueryBuilder keyword(String keyword) {
        this.keyword = keyword;
        return this;
    }
    
    
}
