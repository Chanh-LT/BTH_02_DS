/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quizapp_2451012015;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class Quizapp_2451012015 extends Application {
    
    private ThemeFactory currentTheme;
    private ThemeType curThemeType = ThemeType.DEFAULT_THEME;
    private Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        this.mainStage = stage;
        this.currentTheme =  ThemeFactoryProducer.getFactory(curThemeType);
        showHome();
    }

    private void showHome() {
        Label title = new Label("QUIZ APP");
        title.setStyle(currentTheme.getTitle());
        
        Button btnQuestion = new Button("Quản lý câu hỏi");
        Button btnPractice = new Button("Luyện đề");
        Button btnExam = new Button("Luyện thi");
        
        btnQuestion.setPrefWidth(250);
        btnPractice.setPrefWidth(250);
        btnExam.setPrefWidth(250);        
        
        btnQuestion.setStyle(currentTheme.getButtonStyle());
        btnPractice.setStyle(currentTheme.getButtonStyle());
        btnExam.setStyle(currentTheme.getButtonStyle());
        
        btnQuestion.setOnAction(e -> {
            MyAlert.getInstance().showMessages("Dùng để quản lý câu hỏi");
        });
        
        btnPractice.setOnAction(e -> {
            MyAlert.getInstance().showMessages("Dùng để luyện đề");
        });
                
        btnExam.setOnAction(e -> {
            MyAlert.getInstance().showMessages("Dùng để luyện thi");
        });
        
        ComboBox<ThemeType> cbTheme = new ComboBox<>();
        cbTheme.getItems().addAll(ThemeType.DEFAULT_THEME, ThemeType.DARK_THEME, ThemeType.LIGHT_THEME);
        cbTheme.setValue(ThemeType.DEFAULT_THEME);
        cbTheme.setPrefWidth(250);
        
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle(currentTheme.getBackgroundStyle());
        root.getChildren().addAll(title, btnQuestion, btnPractice, btnExam, cbTheme);        
        
        cbTheme.setOnAction(e ->{
            curThemeType = cbTheme.getValue();
            currentTheme = ThemeFactoryProducer.getFactory(curThemeType);
            title.setStyle(currentTheme.getTitle());
            btnQuestion.setStyle(currentTheme.getButtonStyle());
            btnPractice.setStyle(currentTheme.getButtonStyle());
            btnExam.setStyle(currentTheme.getButtonStyle());
            root.setStyle(currentTheme.getBackgroundStyle());
        });
        
        Scene scene = new Scene(root, 640, 480);
        mainStage.setScene(scene);
        mainStage.setTitle("Hello Javafx");
        mainStage.show();
    }
}
