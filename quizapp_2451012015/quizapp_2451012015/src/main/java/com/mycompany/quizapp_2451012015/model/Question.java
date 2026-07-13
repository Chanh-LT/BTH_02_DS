/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quizapp_2451012015.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Question implements Cloneable{
    private String content;
    private String hint;
    private String level;
    private String category;
    private String image;
    private List<Choice> choices;

    public Question(String content, String hint, String level, String category, String image, List<Choice> choices) {
        this.content = content;
        this.hint = hint;
        this.level = level;
        this.category = category;
        this.image = image;
        this.choices = choices;
    }
 
    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
        Question copy = (Question) super.clone();
        copy.setChoices(new ArrayList<>(this.getChoices()));
        return copy;
    } catch (CloneNotSupportedException ex) {
        Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }   
    }



    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the hint
     */
    public String getHint() {
        return hint;
    }

    /**
     * @param hint the hint to set
     */
    public void setHint(String hint) {
        this.hint = hint;
    }

    /**
     * @return the level_id
     */
    public String getLevel_id() {
        return getLevel();
    }

    /**
     * @param level_id the level_id to set
     */
    public void setLevel_id(String level_id) {
        this.setLevel(level_id);
    }

    /**
     * @return the category_id
     */
    public String getCategory_id() {
        return getCategory();
    }

    /**
     * @param category_id the category_id to set
     */
    public void setCategory_id(String category_id) {
        this.setCategory(category_id);
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the choices
     */
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * @param choices the choices to set
     */
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    public static class Builder {

        public Builder() {
        }

        public Object content(String content) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }


    
}
