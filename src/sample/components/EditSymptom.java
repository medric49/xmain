package sample.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import sample.controllers.components.EditSymptomController;

import java.io.IOException;

public class EditSymptom {

    private FXMLLoader loader;
    private Parent node;
    public EditSymptom() {
        this.loader = new FXMLLoader(sample.Main.class.getResource("views/editSymptom.fxml"));
        try {
            this.node = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EditSymptomController getController() {
        return  this.loader.getController();
    }

    public Parent node() {
        return node;
    }
}
