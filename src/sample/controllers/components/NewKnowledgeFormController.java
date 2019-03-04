package sample.controllers.components;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import sample.components.EditSymptom;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewKnowledgeFormController implements Initializable {
    @FXML
    public JFXTextField editProblem;
    @FXML
    public JFXTextField editSolution;
    @FXML
    public JFXButton save;
    @FXML
    public VBox symptomList;

    public ArrayList<EditSymptom> editSymptoms=  new ArrayList<>();

    @FXML
    private void addSymptom() {
        EditSymptom editSymptom = new EditSymptom();

        this.editSymptoms.add(editSymptom);
        this.symptomList.getChildren().add(editSymptom.node());

        editSymptom.getController().supp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                (NewKnowledgeFormController.this).editSymptoms.remove(editSymptom);
                (NewKnowledgeFormController.this).symptomList.getChildren().remove(editSymptom.node());
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.addSymptom();
    }
}
