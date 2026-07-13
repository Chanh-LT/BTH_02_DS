/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quizapp_2451012015;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.quizapp_2451012015.model.Category;
import com.mycompany.quizapp_2451012015.model.Choice;
import com.mycompany.quizapp_2451012015.model.JsonQuestionDTO;
import com.mycompany.quizapp_2451012015.model.JsonChoiceDTO;
import com.mycompany.quizapp_2451012015.model.Level;
import com.mycompany.quizapp_2451012015.model.Question;
import static com.sun.javafx.animation.KeyValueHelper.getType;
import java.io.IOException;
import java.io.Reader;
import static java.lang.Character.getType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import static javax.xml.catalog.CatalogResolver.NotFoundAction.getType;


/**
 *
 * @author admin
 */
public class FileQuestionParser {
    private String path;
    
    public List<Question> parse(Category c, Level lv) throws IOException{
        if(c == null)
            throw new IllegalArgumentException("Danh muc khong duoc chon");
        if (lv == null) {
            throw new IllegalArgumentException("Do kho khong duoc chon");
        }
        Path jsonPath = Path.of(this.path);
        if(Files.exists(jsonPath)){
            throw new IOException("Couldn't find Json: " + path);
        }
        Gson gson = new Gson();
        Type typeOfT = new TypeToken<List<JsonQuestionDTO>>() {}.getType();
        
        try(Reader reader = Files.newBufferedReader(jsonPath, StandardCharsets.UTF_8)) {
            List<JsonQuestionDTO> jsonQuestionDTO;         
            jsonQuestionDTO = gson.fromJson(reader, typeOfT);
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < JsonQuestionDTO.size(); i++) {
            JsonQuestionDTO dto = JsonQuestionDTO.get(i);
            List<Choice> choices = new ArrayList<>();
            for (JsonChoiceDTO choice: dto.getChoices()) {
                choices.add(new Choice(choice.getContent().trim(), choice.isCorrect()));
            }
            //Question question = new Question.Builder()
                    //.content(dto.getContent())
                    //.hint(dto.getHint())
                    //.category(c.getName())
                    //.level(lv.getName())
                    //.build();
            //questions.add(question);
            }
            return questions;
        }
    }
}
