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
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Daemon;

/**
 * FXML Controller class
 *
 * @author Yvan TCHOFFO
 */
public class DiscussionController implements Initializable {

    private Stage stage;
    @FXML
    private Button togglemenu;
    @FXML
    private VBox sidemenu;
    @FXML
    private ImageView toggleimg;
    @FXML 
    private JFXTextField answer;
    @FXML
    private VBox discussion;
    private Daemon daemon;

    public VBox getDiscussion() {
        return discussion;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public Stage getStage(){
        return this.stage;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        HBox messagebot = new HBox();
        messagebot.getStyleClass().add("message-block");
        messagebot.getStyleClass().add("messageblock-bot");
        Label label = new Label("Bienvenu sur X-main.");
        messagebot.getChildren().add(label);
        discussion.getChildren().add(messagebot);
        
        daemon = new Daemon("daemon");
        daemon.setController(this);
        Platform.runLater(daemon);
//        daemon.start();
    }    

    public void addquestion(String question){
        HBox messagebot = new HBox();
        messagebot.getStyleClass().add("message-block");
        messagebot.getStyleClass().add("messageblock-bot");
        Label label = new Label(question);
        messagebot.getChildren().add(label);
        discussion.getChildren().add(messagebot);
    }
    
    public void addreponse(String rep){
        HBox messagebot = new HBox();
        messagebot.getStyleClass().add("message-block");
        messagebot.getStyleClass().add("messageblock-user");
        Label label = new Label(rep);
        messagebot.getChildren().add(label);
        discussion.getChildren().add(messagebot);
    }
    
    @FXML
    private void send(ActionEvent event) {
        String answer = this.answer.getText();
        if(answer.toLowerCase().equals("oui") || answer.toLowerCase().equals("non")){
            //logique manquante
            Engine.handleRep(answer.toLowerCase());
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
    private void togglemenu(ActionEvent event) {
        double length = this.sidemenu.getWidth();
        if(length == 0){
            this.sidemenu.setStyle("-fx-pref-width: 235;");
            this.toggleimg.setImage(new Image(sample.Main.class.getResource("views/icons/close.png").toExternalForm()));
            
        }
        else{
            this.sidemenu.setStyle("-fx-pref-width: 0;");
            this.toggleimg.setImage(new Image(sample.Main.class.getResource("views/icons/menu-button.png").toExternalForm()));
        }
        
    }
    
    public void setMain() throws IOException{
        this.stage.hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(sample.Main.class.getResource("views/main.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        this.stage.setTitle("X-main");
        this.stage.setScene(new Scene(root));
        this.stage.show();
        controller.setStage(this.stage);
    }
    
}
