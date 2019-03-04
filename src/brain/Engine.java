/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brain;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
import org.jpl7.Query;
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
    
    public synchronized void start() throws IOException {

        String s = "consult('"+BrainProcessing.BRAIN_FILE+"')";
        Query q1 = new Query(s);
        q1.hasSolution();

        Query q2 = new Query("go.");
        q2.hasSolution();
        
    }    
    public static void handle(String question) throws UnsupportedEncodingException{
        /*  
         * 
         * 
         * Tchoffo write tes ways
         * 
         * 
         */
        
        // ici tu appeles la methode respond de Engine pour envoyer la reponse a Prolog
        question = question.substring(1, question.length()-1);
        question = " Est-ce que la machine "+question+" ?  ";
        
        System.out.println("> "+question);
        controller.addquestion(question);
        ButtonType menu = new ButtonType("Interrompre");
        Alert a = new Alert(Alert.AlertType.NONE, null, ButtonType.YES, ButtonType.NO, menu);
        
        double x = controller.getStage().getX();
        double y = controller.getStage().getY();
        double w = controller.getStage().getWidth();
        double h = controller.getStage().getHeight();
        
        a.initStyle(StageStyle.TRANSPARENT);
        
        a.getDialogPane().setPrefSize(0, 0);
        a.setX(x+w/2);
        a.setY(y+h-110);
        
        Optional<ButtonType> result = a.showAndWait();
        
        if(result.get() == ButtonType.YES){
            Engine.respond("oui");
            controller.addreponse("oui");
        } else if(result.get() == ButtonType.NO) {
            Engine.respond("non");
            controller.addreponse("non");
        } else if(result.get() == menu){
            Engine.respond("cancel");
            Engine.cancel = true;
        }
    }

    public static void handleRep(String rep){
        if(!Engine.cancel){
            rep = rep.substring(1, rep.length()-1);
            controller.addquestion(" Il faut "+rep+" ");
            Engine.cancel = false;
        } else {
            controller.addquestion(" Opération annulée par l'utilisateur ");
        }
    }
    
    public String getSolution(){
        return sol;
    }
    
    public String nextQuestion(){
        return "La machine "+msg+" ?";
    }
    public static boolean cancel=false;
    public static String res = "";
    
    public static String readT() throws InterruptedException{
        Scanner s = new Scanner(System.in);
        return s.next();
    }
    
    public static void respond(String rep){
        System.setIn(new ByteArrayInputStream(rep.getBytes()));
    }
}
