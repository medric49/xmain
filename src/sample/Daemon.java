/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import brain.Engine;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sample.controllers.DiscussionController;

/**
 *
 * @author Yvan TCHOFFO
 */
public class Daemon extends Thread{
    public Engine engine;
    public DiscussionController dc = null;
    public Daemon(String name){
        super(name);
    }
    
    public void setController(DiscussionController c){
        dc = c;
    }
    
    public void run() {
        if(dc != null){
            engine = new Engine();
            engine.setController(dc);
            try {
                engine.start();
            } catch (IOException ex) {
                Logger.getLogger(Daemon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
