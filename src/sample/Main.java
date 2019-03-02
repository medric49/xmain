package sample;

import brain.BrainProcessing;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Knowledge;
import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        //launch(args);

        /*
        String problem = "clavier";
        String solution = "changer de clavier";

        ArrayList<String> symptoms = new ArrayList<>();
        symptoms.add("la machine s'eteint");
        symptoms.add("la machine s'allume");

        Knowledge knowledge = BrainProcessing.save(problem, solution, symptoms);
        knowledge.delete();
        */

        Query query = new Query("consult", new Term[]{new Atom("brain.pl")});
        query.hasSolution();
        Query query1 = new Query("go");
        System.out.println(query1.hasSolution());
        System.exit(0);
    }
}
