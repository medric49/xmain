/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Yvan TCHOFFO
 */
public class MainController implements Initializable {
    
    @FXML
    public JFXButton txt;
    
    private Stage stage;

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
    private void diagnose(ActionEvent event) throws Exception {
        this.stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(sample.Main.class.getResource("views/discussion.fxml"));
        Parent root = loader.load();
        DiscussionController controller = loader.getController();
        this.stage.setScene(new Scene(root));
        this.stage.show();
        controller.setStage(this.stage);
    }
    
    @FXML
    private void addproblem(ActionEvent event) throws IOException {
        this.stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(sample.Main.class.getResource("views/form.fxml"));
        Parent root = loader.load();
        FormController controller = loader.getController();
        this.stage.setScene(new Scene(root));
        this.stage.show();
        controller.setStage(this.stage);
    }
    
}
