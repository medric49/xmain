package sample.controllers.components;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class KnowledgeDetailsController implements Initializable {

    @FXML
    private Label problem;
    @FXML
    private VBox symptomsContainer;
    @FXML
    private Text solution;
    @FXML
    public JFXButton delete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void initializeText(String problem, String solution, ArrayList<String> symptoms) {
        this.problem.setText(problem);
        this.solution.setText(solution);

        for (int i = 0; i<symptoms.size(); i++ ) {
            this.symptomsContainer.getChildren().add(new Text(symptoms.get(i)));
        }
    }
}
