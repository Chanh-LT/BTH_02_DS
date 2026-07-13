/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quizapp_2451012015;

import com.mycompany.quizapp_2451012015.model.ThemeType;
import com.mycompany.quizapp_2451012015.model.Choice;
import com.mycompany.quizapp_2451012015.model.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private List<Question> practiceQuestion = new ArrayList<>();
    private QuestionQueryBuilder queryBuilder = new QuestionQueryBuilder();
    private int currQuestionIdx = 0;
    private TableView tblQuestion;
    private ObservableList<Question> questionTableData;
    
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
               
        System.out.println("SQL: " + queryBuilder.levelId(1).categoryId(1).toString());
        
        btnQuestion.setStyle(currentTheme.getButtonStyle());
        btnPractice.setStyle(currentTheme.getButtonStyle());
        btnExam.setStyle(currentTheme.getButtonStyle());
        
        btnQuestion.setOnAction((e -> showQuestionMgtForm()));
        
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
        
        TextField txtHint = new TextField();
        txtHint.setPromptText("Nhập gợi ý: ");
        
        ComboBox<String> cbCategory = new ComboBox<>();
        cbCategory.getItems().addAll("Grammar", "Volcaburay", "Reading");
        cbCategory.setValue("Danh mục");
        
        ComboBox<String> cbLevel = new ComboBox<>();
        cbLevel.getItems().addAll("Dễ", "Trung bình", "Khó");
        cbLevel.setValue("Độ khó");
        

        HBox hbCombo = new HBox(cbCategory, cbLevel);  
        hbCombo.setAlignment(Pos.CENTER);
        
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
        row1.setAlignment(Pos.CENTER);
        HBox row2 = new HBox(rdC, txtC, rdD, txtD);
        row2.setAlignment(Pos.CENTER);
        
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
            
            
            //int category_id = getCategoryId(cbCategory.getValue());
            //int level_id = getLevel_Id(cbLevel.getValue());
            String correctAnswer = correctGroup.getSelectedToggle().getUserData().toString();
            
            ArrayList<Choice> choices = new ArrayList<>();
            choices.add(new Choice(txtA.getText().trim(),correctAnswer.equals("A")));
            choices.add(new Choice(txtB.getText().trim(),correctAnswer.equals("B")));
            choices.add(new Choice(txtC.getText().trim(),correctAnswer.equals("C")));
            choices.add(new Choice(txtD.getText().trim(),correctAnswer.equals("D")));
            
            Question question = new Question(txtQuestion.getText().trim(),txtHint.getText().trim(), cbLevel.getValue(), cbCategory.getValue(), "", choices);
            
//            boolean result = addQuestionToDB(txtQuestion.getText().trim(), txtHint.getText().trim(), 1, 1, 
//                            txtA.getText().trim(), txtB.getText().trim(), txtC.getText().trim(), 
//                            txtD.getText().trim(), correctAnswer);
            
            boolean result = addQuestionToDB(question);
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
        Button btnAddFromFile = new Button("Thêm từ tập tin");
        
        HBox hbBtn = new HBox (btnBack, btnAddQuestion, btnAddFromFile);
        hbBtn.setAlignment(Pos.CENTER);
        
        TextField txtSearch = new TextField("Tìm câu hỏi");
       
        ComboBox<String> cbSearchCategory = new ComboBox<>();
        cbSearchCategory.getItems().addAll("Grammar", "Volcaburay", "Reading");
        cbSearchCategory.setValue("Danh mục");
        
        ComboBox<String> cbSearchLevel = new ComboBox<>();
        cbSearchLevel.getItems().addAll("Dễ", "Trung bình", "Khó");
        cbSearchLevel.setValue("Độ khó");
        
        HBox hbSearch = new HBox(txtSearch, cbSearchCategory, cbSearchLevel);
        hbSearch.setAlignment(Pos.CENTER);

        createTableQuestion();
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle(currentTheme.getBackgroundStyle());
        root.getChildren().addAll(title, txtQuestion, txtHint, hbCombo, hbBtn, row1, row2, hbSearch, tblQuestion);  
        
        Scene scene = new Scene(root, 640, 480);
        this.mainStage.setScene(scene);
        this.mainStage.show();
        
        //loadQuestionTable(txtSearch, cbCategorySearch, cbLevelSearch);
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
    
    private boolean addQuestionToDB(Question question) {
        try {
            Connection conn = JdbcConnector.getInstance().connect();
            String sqlQuestion =  "INSERT INTO question(content, hint, image, category_id, level_id) " + "VALUES (?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sqlQuestion, Statement.RETURN_GENERATED_KEYS);
            
            int category_id = getCategoryId(question.getCategory());
            int level_id = getLevel_Id(question.getLevel());
            
            stmt.setString(1, question.getContent());
            stmt.setString(2, question.getHint());
            stmt.setString(3, "");
            stmt.setString(4, question.getCategory());
            stmt.setString(5, question.getLevel());
            
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            int questionId = -1;
            if(rs.next()) {
                questionId = rs.getInt(1);
            }
            
            if(questionId == -1 )
                return false;
            
            for (Choice choice : question.getChoices()) {
                choice.setQuestion_id(questionId);
                insertChoice(conn, choice);
            }
//            insertChoice(conn, strA, correctAnswer.equals("A"), questionId);
//            insertChoice(conn, strB, correctAnswer.equals("B"), questionId);
//            insertChoice(conn, strC, correctAnswer.equals("C"), questionId);
//            insertChoice(conn, strD, correctAnswer.equals("D"), questionId);
            
            
            
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
    
    private void insertChoice(Connection conn, Choice choice) throws SQLException {
        String sqlChoice = "INSERT INTO choice(content, is_correct, question_id)" + "VALUES(?,?,?)";
        try {
            PreparedStatement stmt =  conn.prepareStatement(sqlChoice);
            stmt.setString(1, choice.getContent());
            stmt.setBoolean(2, choice.isIsCorrect());
            stmt.setInt(3, choice.getQuestion_id());
        } catch (SQLException ex) {
            Logger.getLogger(Quizapp_2451012015.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private Integer getCategoryId(String val) {
        if(val == null || val.equals("Eveything"))
            return null;
        
        return switch (val) {
            case "Grammer" ->
                1;
            case "Vocabulary" ->
                2;
            case "reading" ->
                3;
            default ->
                 null;
        };
    }

    private Integer getLevel_Id(String val) {
        return switch (val) {
            case "Dễ" ->
                1;
            case "Vocabular" ->
                2;
            case "reading" -> 
                3;
            default ->
                1;
        };
    }

    private void showPracticeForm() {
        Label title = new Label("Thiết lập câu hỏi");
        title.setAlignment(Pos.CENTER);
        
        TextField txtKeyWord = new TextField();
        txtKeyWord.setPromptText("Nhập keyword vào");
        txtKeyWord.setMaxWidth(300);
        
        ComboBox<String> cbLevel = new ComboBox<>();
        cbLevel.getItems().addAll("All", "Easy", "Medium", "Hard");
        
        ComboBox<String> cbCategory = new ComboBox<>();
        cbCategory.getItems().addAll("Everything", "Grammar", "Vocabulary", "Reading");
        
        TextField txtNumber = new TextField();
        txtNumber.setPromptText("Nhập số câu hỏi: ");
        
        Button btnBack = new Button("Return");
        btnBack.setOnAction(e -> showHome());
        Button btnStart = new Button("Activating Training System");
        btnStart.setOnAction(e -> { int number = Integer.parseInt(txtNumber.getText().trim());
            Integer categoryId = getCategoryId(cbCategory.getValue());
            Integer levelId = getLevel_Id(cbCategory.getValue());
            QuestionRepository repo = new QuestionRepository();
            List<Question> practiceQuestion = repo.getQuestionByFilter(txtKeyWord.getText(), categoryId, levelId, number);
            if(practiceQuestion.isEmpty()) {
                MyAlert.getInstance().showMessages("Không có câu hỏi nào được chọn");
                return;
            }
            else {
                MyAlert.getInstance().showMessages("Amount of câu hỏi: " + practiceQuestion.size());
            }
        });
        
        
        VBox root = new VBox(15);
        root.setStyle(currentTheme.getBackgroundStyle());
        root.getChildren().addAll(title, txtKeyWord, txtNumber, cbLevel, btnBack, btnStart);  
        
        Scene scene = new Scene(root, 640, 480);
        this.mainStage.setScene(scene);
    }
    
    private void showPracticeQuestion() {
        Question q = practiceQuestion.get(currQuestionIdx);
        Label title = new Label("Question: " + currQuestionIdx + 1 + "/" +practiceQuestion.size());
        Label question = new Label(q.getContent());
        question.setWrapText(true);
        question.setMaxWidth(500);
    }

    private void createTableQuestion() {
        tblQuestion = new TableView();
        TableColumn<Question, Integer> coldId = new TableColumn<>("ID");
        coldId.setCellValueFactory(new PropertyValueFactory<>("id"));
        coldId.setPrefWidth(200);
        
        TableColumn<Question, String> coldContent = new TableColumn<>("Nội dung");
        coldId.setCellValueFactory(new PropertyValueFactory<>("content"));
        
        TableColumn<Question, String> coldCategory = new TableColumn<>("Danh mục");
        coldId.setCellValueFactory(new PropertyValueFactory<>("category"));
        
        TableColumn<Question, String> coldLevel = new TableColumn<>("Độ khó");
        coldId.setCellValueFactory(new PropertyValueFactory<>("level"));
        
        coldId.setPrefWidth(200);
        
        tblQuestion.getColumns().addAll(coldId, coldContent, coldCategory, coldLevel);
        
        //Them danh sach cau hoi
        tblQuestion.setItems(questionTableData);
        tblQuestion.setPlaceholder(new Label("Chưa có câu hỏi"));
        
    }
    
    private void loadQuestionTable(TextField txtSearch, ComboBox<String> cbCategorySearch, ComboBox<String> cbCategoryLevel) {
        String keyword = "";
        if(txtSearch.getText().trim().isEmpty()) {
            keyword = txtSearch.getText().trim();
        }
        Integer categoryId = null;
        Integer levelId = null;
        if(cbCategorySearch != null) {
            categoryId = getCategoryId(cbCategorySearch.getValue());
        }
        if (cbCategoryLevel != null) {
            levelId = getCategoryId(cbCategoryLevel.getValue());
        }
        QuestionRepository repository = new QuestionRepository();
        List<Question> questions = repository.getQuestionByFilter(keyword, categoryId, levelId, 100);
        questionTableData.setAll(questions);
    }
}