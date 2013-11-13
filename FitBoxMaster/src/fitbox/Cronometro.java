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
    final double constanteTiempo = 0.321;
    int segundos, minutos, horas, centesimas;
    double puntos;
    RealizarActividadController padre;
    boolean finalizado = false;

    public Cronometro(RealizarActividadController padre) {
        this.padre = padre;
    }
    
    public void run() {
        try {
            Thread.sleep(5500);
            for (;;) {
                if(!finalizado){
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
                }
            }
            
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    
    }
    
        public void finalize(){
        if(!finalizado){
            this.finalizado=true;            
        }
        }
    public void resume(){
        if(finalizado){
            this.finalizado=false;
        }
    }
    public double getPuntos(){
        puntos = (((horas*3600)+(minutos*60)+segundos)*constanteTiempo);
        return puntos;
    }
}