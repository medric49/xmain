package sample.controllers.components;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class EditSymptomController implements Initializable {
    @FXML
    public JFXTextField edit;
    @FXML
    public JFXButton supp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
