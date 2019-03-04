package sample.controllers;

import brain.BrainProcessing;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Knowledge;
import sample.components.KnowledgeDetails;
import sample.components.KnowledgeItem;
import sample.components.NewKnowledgeForm;
import sample.controllers.components.NewKnowledgeFormController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class FormController implements Initializable {
    private Stage stage;

    @FXML
    private ScrollPane scroller;
    @FXML
    private VBox knowledgeContainer;
    @FXML
    private AnchorPane defaultPane;
    @FXML
    private VBox sidemenu;
    @FXML
    private StackPane centerPane;
    @FXML
    private JFXButton buttonAdd;

    private ArrayList<Knowledge> knowledges;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scroller.setContent(this.knowledgeContainer);
        scroller.setFitToHeight(true);
        scroller.setFitToWidth(true);

        knowledges = BrainProcessing.getKnowledges();

        setForm();

        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setForm();
            }
        });

        actualize();
    }


    public void setStage(Stage stage) {
        this.stage = stage;
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


    private void setForm() {
        centerPane.getChildren().clear();
        NewKnowledgeForm newKnowledgeForm = new NewKnowledgeForm();
        centerPane.getChildren().add(newKnowledgeForm.node());

        newKnowledgeForm.getController().save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NewKnowledgeFormController controller = newKnowledgeForm.getController();

                String problem = controller.editProblem.getText();
                String solution = controller.editSolution.getText();
                ArrayList<String> symptoms = new ArrayList<>();
                for (int i = 0; i < controller.editSymptoms.size(); i++ ) {
                    symptoms.add(controller.editSymptoms.get(i).getController().edit.getText());
                }
                boolean f = true;
                for (int i = 0; i<symptoms.size(); i++) {
                    if (symptoms.get(i).isEmpty())
                    {
                        f = false;
                        break;
                    }
                }

                if (!problem.isEmpty() && !solution.isEmpty() && f) {
                    save(problem, solution, symptoms);
                    setForm();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Erreur de saisie");
                    alert.setHeaderText(null);
                    alert.setContentText("Tous les champs sont obligatoires.");
                    alert.showAndWait();
                }
            }
        });
    }

    private void setDetails(Knowledge knowledge) {
        centerPane.getChildren().clear();
        KnowledgeDetails knowledgeDetails = new KnowledgeDetails(knowledge);
        centerPane.getChildren().add(knowledgeDetails.node());
        knowledgeDetails.getController().delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous vraiment supprimer cette connaissance ?");
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get().getButtonData().isDefaultButton())
                    delete(knowledge);

            }
        });
    }

    private void save(String problem, String solution, ArrayList<String> symptoms) {
        BrainProcessing.save(problem, solution, symptoms);

        actualize();
    }

    private void actualize() {
        this.sidemenu.getChildren().remove(scroller);
        this.sidemenu.getChildren().remove(defaultPane);

        this.knowledges = BrainProcessing.getKnowledges();
        if (knowledges.size()>0) {
            sidemenu.getChildren().add(scroller);

            knowledgeContainer.getChildren().clear();
            for (int i = 0; i<knowledges.size(); i++) {
                KnowledgeItem item = new KnowledgeItem(knowledges.get(i));
                knowledgeContainer.getChildren().add(item.node() );
                item.node().setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        setDetails((item.getKnowledge()));
                    }
                });
            }

        }
        else {
            sidemenu.getChildren().add(defaultPane);
        }
    }

    private void delete(Knowledge knowledge) {
        BrainProcessing.delete(knowledge);
        setForm();
        actualize();
    }
}
