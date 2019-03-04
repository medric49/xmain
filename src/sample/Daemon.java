/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import brain.Engine;

/**
 *
 * @author Yvan TCHOFFO
 */
public class Daemon extends Thread{
    public Daemon(String name){
        super(name);
    }
    
    public void run(){
        Engine engine = new Engine();
        engine.start();
    }
    
}
