package sample.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.Knowledge;
import sample.controllers.components.KnowledgeItemController;

import java.io.IOException;

public class KnowledgeItem {
    private FXMLLoader loader;
    private Knowledge knowledge;
    private KnowledgeItemController controller;
    private Parent node;

    public KnowledgeItem(Knowledge knowledge) {
        loader = new FXMLLoader(sample.Main.class.getResource("views/knowledgeItem.fxml"));
        this.knowledge = knowledge;

        try {
            this.node = loader.load();
            this.controller = loader.getController();
            this.controller.setLabels(knowledge.getProblem(), knowledge.getSymptoms().size()+" symptome(s)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Parent node() {
        return node;
    }

    public Knowledge getKnowledge() {
        return knowledge;
    }
    public KnowledgeItemController getController() {
        return controller;
    }
}
