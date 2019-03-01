package sample;

import brain.BrainProcessing;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/main.fxml"));
        primaryStage.setTitle("X-main");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        /*String problem = "clavier";
        String solution = "changer de clavier";

        ArrayList<String> symptoms = new ArrayList<>();
        symptoms.add("la machine s'eteint");
        symptoms.add("la machine s'allume");

        BrainProcessing.save(problem, solution, symptoms);
        System.exit(0);*/
    }
}
