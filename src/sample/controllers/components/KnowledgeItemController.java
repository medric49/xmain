package sample.controllers.components;

import com.jfoenix.controls.JFXRippler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class KnowledgeItemController implements Initializable {

    @FXML
    private StackPane pane;

    @FXML
    private VBox vbox;
    @FXML
    private Label title;
    @FXML
    private Label details;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JFXRippler rippler = new JFXRippler(this.vbox);
        this.pane.getChildren().add(rippler);

    }

    public void setLabels(String title, String details) {
        this.title.setText(title);
        this.details.setText(details);
    }
}
