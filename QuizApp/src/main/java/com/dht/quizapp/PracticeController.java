/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dht.quizapp;

import com.dht.pojo.Question;
import com.dht.services.QuestionServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class PracticeController implements Initializable {

    @FXML
    private Text txtContent;
    @FXML
    private VBox vboxChoices;

    private static final QuestionServices questionServices = new QuestionServices();

    private List<Question> questions;
    private int currentQuestion = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.questions = questionServices.getQuestions(2);
            this.loadQuestion();
        } catch (SQLException ex) {
            Logger.getLogger(PracticeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadQuestion() {
        Question q = this.questions.get(this.currentQuestion);
        this.txtContent.setText(q.getContent());

//        this.vboxChoices.getChildren().clear();
        ToggleGroup t = new ToggleGroup();
        for (var c : q.getChoices()) {
            RadioButton r = new RadioButton(c.getContent());
            r.setToggleGroup(t);
            this.vboxChoices.getChildren().add(r);
        }
    }
}
