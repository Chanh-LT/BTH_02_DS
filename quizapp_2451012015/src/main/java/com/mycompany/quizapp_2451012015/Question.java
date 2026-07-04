/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quizapp_2451012015;

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
    private int level_id;
    private int category_id;
    private String image;
    private List<Choice> choices;

    public Question(String content, String hint, int level_id, int category_id, String image, List<Choice> choices) {
        this.content = content;
        this.hint = hint;
        this.level_id = level_id;
        this.category_id = category_id;
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
    public int getLevel_id() {
        return level_id;
    }

    /**
     * @param level_id the level_id to set
     */
    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    /**
     * @return the category_id
     */
    public int getCategory_id() {
        return category_id;
    }

    /**
     * @param category_id the category_id to set
     */
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
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


    
}
