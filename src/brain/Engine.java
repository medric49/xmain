/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brain;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.ByteArrayInputStream;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.fli.Prolog;
import org.jpl7.fli.term_t;
import sample.controllers.DiscussionController;

/**
 *
 * @author nivekiba
 */


public class Engine {
    
    private static String msg = "";
    private static String sol = "";
    private static DiscussionController controller;
    public static boolean waiting = false;
    public Engine() {}

    public void setController(DiscussionController controller) {
        this.controller = controller;
    }
    
    public void start() {

        String s = "consult('"+BrainProcessing.BRAIN_FILE+"')";
        Query q1 = new Query(s);
        q1.hasSolution();

        Query q2 = new Query("go.");
        q2.oneSolution();
    }    
    public static void handle(String question){
        /*  
         * 
         * 
         * Tchoffo write tes ways
         * 
         * 
         */
        
        // ici tu appeles la methode respond de Engine pour envoyer la reponse a Prolog
        System.out.println(question);
        while(waiting){
            //on attend la réponse utilisateur;
        }
        
        
    }

    public static void handleRep(String rep){
        sol = rep;
        /**
         * 
         * 
         * Ici tu ecris ce qui se passe quand on trouve la solution
         * 
         */
    }
    
    public String getSolution(){
        return sol;
    }
    
    public String nextQuestion(){
        return "La machine "+msg+" ?";
    }
    
    public static void respond(String rep){
        System.setIn(new ByteArrayInputStream(rep.getBytes()));
    }
    
    public void close() {
        Query q2 = new Query("halt");
        q2.oneSolution();
    }
}
