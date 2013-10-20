/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox;

import fitbox.controller.RealizarActividadController;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;

/**
 *
 * @author Jose
 */
public class Cronometro implements Runnable {
    int segundos, minutos, horas, centesimas;
    RealizarActividadController padre;
    boolean pausado;

    public Cronometro(RealizarActividadController padre) {
        this.padre = padre;
    }
    
    public void run() {
        try {
            for (;;) {
                pausado = false;
                if (centesimas == 99){
                    centesimas = 0;
                    segundos++;
                }
                if (segundos == 59) {
                    segundos = 0;
                    minutos++;
                }
                if (minutos == 59) {
                    minutos = 0;
                    horas++;
                }
                centesimas++;
                
                padre.actualizaLabels(horas,minutos,segundos,centesimas);
                sleep(10);
                System.out.println(this.toString());
            }
            
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    
    }

    public void resume(){
        pausado=false;
        resume();
    }
    public void pause(){
        if(pausado){
            pausado=true;
            //wait();
        }

    }
    
    public void finalize(){
        this.finalize();
    }
}