package sample;

import brain.BrainProcessing;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.MainController;
import model.Knowledge;
import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(sample.Main.class.getResource("views/main.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        primaryStage.setTitle("X-main");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        controller.setStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
