package sample.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.Knowledge;
import sample.controllers.components.KnowledgeDetailsController;

import java.io.IOException;

public class KnowledgeDetails {

    private FXMLLoader loader;
    private KnowledgeDetailsController controller;
    private Parent node;

    private Knowledge knowledge;

    public KnowledgeDetails(Knowledge knowledge) {
        this.loader = new FXMLLoader(sample.Main.class.getResource("views/knowledgeDetails.fxml"));
        try {
            this.node = this.loader.load();
            this.controller = this.loader.getController();

            this.controller.initializeText(knowledge.getProblem(),knowledge.getSolution(), knowledge.getSymptoms());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Parent node() {
        return this.node;
    }

    public KnowledgeDetailsController getController() {
        return this.controller;
    }

    public Knowledge getKnowledge() {
        return this.knowledge;
    }
}
