/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import brain.Engine;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Yvan TCHOFFO
 */
public class DiscussionController implements Initializable {

    private Stage stage;
    @FXML
    private ImageView returnToMain;
    @FXML
    private VBox sidemenu;
    @FXML
    private ImageView toggleimg;
    @FXML
    private JFXTextField answer;
    @FXML
    private VBox discussion;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void send(ActionEvent event) {
        String answer = this.answer.getText();
        if(answer.toLowerCase().equals("oui") || answer.toLowerCase().equals("non")){
            //logique manquante
        }else{
            this.answer.setText("");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez répondre par 'oui' ou par 'non'");
            alert.showAndWait();
        }
    }

    @FXML
    private void backtohome(MouseEvent event) throws IOException {
        this.stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(sample.Main.class.getResource("views/main.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        this.stage.setScene(new Scene(root));
        this.stage.show();
        controller.setStage(this.stage);
    }

    
}
