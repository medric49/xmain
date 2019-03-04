package sample.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import sample.controllers.components.NewKnowledgeFormController;

import java.io.IOException;

public class NewKnowledgeForm {
    private FXMLLoader loader;
    private NewKnowledgeFormController controller;
    private Parent node;

    public NewKnowledgeForm() {
        this.loader = new FXMLLoader(sample.Main.class.getResource("views/newKnowledgeForm.fxml"));
        try {
            this.node = loader.load();
            this.controller = this.loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Parent node() {
        return node;
    }

    public NewKnowledgeFormController getController() {
        return controller;
    }

}
