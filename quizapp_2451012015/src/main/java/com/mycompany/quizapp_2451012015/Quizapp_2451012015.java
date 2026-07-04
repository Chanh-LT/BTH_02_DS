/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quizapp_2451012015;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
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
        
        btnQuestion.setOnAction((e -> showQuestionMgtForm()));
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
        cbTheme.setOnAction(e ->{
            curThemeType = cbTheme.getValue();
            currentTheme = ThemeFactoryProducer.getFactory(curThemeType);
            showHome();
        });
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
        this.mainStage.setScene(scene);
        this.mainStage.setTitle("Hello Javafx");
        this.mainStage.show();
    }

    private void showQuestionMgtForm() {
        Label title = new Label("QUẢN LÝ CÂU HỎI");
        title.setStyle(currentTheme.getTitle());
        
        TextField txtQuestion = new TextField();
        txtQuestion.setPromptText("Nhập nội dung question: ");
        txtQuestion.setMaxWidth(500);
        txtQuestion.setAlignment(Pos.TOP_LEFT);
        
        TextField txtHint = new TextField();
        txtHint.setPromptText("Nhập gợi ý: ");
        txtHint.setAlignment(Pos.TOP_LEFT);
        
        ComboBox<String> cbCategory = new ComboBox<>();
        cbCategory.getItems().addAll("Grammar", "Volcaburay", "Reading");
        cbCategory.setValue("Danh mục");
        
        ComboBox<String> cbLevel = new ComboBox<>();
        cbLevel.getItems().addAll("Dễ", "Trung bình", "Khó");
        cbLevel.setValue("Độ khó");

        HBox hbCombo = new HBox(cbCategory, cbLevel);       
        RadioButton rdA = new RadioButton();
        TextField txtA = new TextField();
        txtA.setPromptText("Nhập đáp án A");
        
        RadioButton rdB = new RadioButton();
        TextField txtB = new TextField();
        txtB.setPromptText("Nhập đáp án B");
        
        RadioButton rdC = new RadioButton();
        TextField txtC = new TextField();
        txtC.setPromptText("Nhập đáp án C");
        
        RadioButton rdD = new RadioButton();
        TextField txtD = new TextField();
        txtD.setPromptText("Nhập đáp án D");
        
        ToggleGroup correctGroup = new ToggleGroup();
        
        rdA.setToggleGroup(correctGroup);
        rdA.setUserData("A");
        rdB.setToggleGroup(correctGroup);
        rdB.setUserData("B");
        rdC.setToggleGroup(correctGroup);
        rdC.setUserData("D");
        rdD.setToggleGroup(correctGroup);
        rdD.setUserData("D");
        
        HBox row1 = new HBox(rdA, txtA, rdB, txtB);
        HBox row2 = new HBox(rdC, txtC, rdD, txtD);
        
        Button btnBack = new Button("Return");
        btnBack.setOnAction(e -> {
            showHome();
        });
        
        Button btnAddQuestion = new Button("Thêm question");
        btnAddQuestion.setOnAction(e -> {
            if(txtQuestion.getText().trim().isEmpty() || txtHint.getText().trim().isEmpty()) {
                MyAlert.getInstance().showError("Please nhập câu hỏi và gợi ý");
                return;
            }
               
            if(txtA.getText().trim().isEmpty() || txtA.getText().trim().isEmpty() ) {
                MyAlert.getInstance().showError("Please nhập đáp án");
                return;
            }
            
            if(correctGroup.getSelectedToggle() == null) {
                MyAlert.getInstance().showError("Please chọn đáp án đúng");
            }
            if(cbCategory.getValue() == null || cbLevel.getValue() == null)
                MyAlert.getInstance().showError("Chọn danh mục please");
            
            
            int category_id = getCategoryId(cbCategory.getValue());
            int level_id = getLevel_Id(cbLevel.getValue());
            String correctAnswer = correctGroup.getSelectedToggle().getUserData().toString();
            
            ArrayList<String> choices = new ArrayList<>();
            
            Question question = new Question(txtQuestion.getText().trim(),txtHint.getText().trim(), level_id, category_id, "", choices);
            
            boolean result = addQuestionToDB(txtQuestion.getText().trim(), txtHint.getText().trim(), 1, 1, 
                            txtA.getText().trim(), txtB.getText().trim(), txtC.getText().trim(), 
                            txtD.getText().trim(), correctAnswer);
            
            if(result) {
                MyAlert.getInstance().showMessages("Thêm câu hỏi thành công");
                txtQuestion.clear();
                txtHint.clear();
                txtA.clear();
                txtB.clear();
                txtC.clear();
                txtD.clear();
                cbCategory.setValue(null);
                cbLevel.setValue(null);
                correctGroup.setUserData(null);
            }
            else {
                MyAlert.getInstance().showMessages("Thêm câu hỏi thất bại");
            }
        });
        
        HBox hbBtn = new HBox (btnBack, btnAddQuestion);

        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle(currentTheme.getBackgroundStyle());
        root.getChildren().addAll(title, txtQuestion, txtHint, hbCombo, row1, row2, hbBtn);  
        
        Scene scene = new Scene(root, 640, 480);
        this.mainStage.setScene(scene);
        this.mainStage.show();
    }

    private boolean addQuestionToDB(String questionContent, String hint, int category_id, int level_id, String strA, String strB, String strC, String strD, String correctAnswer) {
        try {
            Connection conn = JdbcConnector.getInstance().connect();
            String sqlQuestion =  "INSERT INTO question(content, hint, image, category_id, level_id) " + "VALUES (?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sqlQuestion, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, questionContent);
            stmt.setString(2, hint);
            stmt.setString(3, "");
            stmt.setInt(4, category_id);
            stmt.setInt(5, level_id);
            
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            int questionId = -1;
            if(rs.next()) {
                questionId = rs.getInt(1);
            }
            
            if(questionId == -1 )
                return false;
            
            insertChoice(conn, strA, correctAnswer.equals("A"), questionId);
            insertChoice(conn, strB, correctAnswer.equals("B"), questionId);
            insertChoice(conn, strC, correctAnswer.equals("C"), questionId);
            insertChoice(conn, strD, correctAnswer.equals("D"), questionId);
            
            
            
            stmt.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Quizapp_2451012015.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private void insertChoice(Connection conn, String choice, boolean correctAnswer, int questionId) throws SQLException {
        String sqlChoice = "INSERT INTO choice(content, is_correct, question_id)" + "VALUES(?,?,?)";
        try {
            PreparedStatement stmt =  conn.prepareStatement(sqlChoice);
            stmt.setString(1, choice);
            stmt.setBoolean(2, correctAnswer);
            stmt.setInt(3, questionId);
        } catch (SQLException ex) {
            Logger.getLogger(Quizapp_2451012015.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getCategoryId(String val) {
        switch (val) {
            case "Grammer":
                return 1;
            case "Vocabulary":
                return 2;
            case "reading":
                return 3;
             default:
                 return 1;
        }
    }

    private int getLevel_Id(String val) {
        switch (val) {
            case "Dễ":
                return 1;
            case "Vocabular":
                return 2;
            case "reading":
                return 3;
             default:
                 return 1;
        }        
    }


}
