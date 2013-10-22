package fitbox.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jose
 */
public class Ranking {
    private final SimpleStringProperty nombre;
    private final SimpleDoubleProperty puntos;
 
    private Ranking(String nombre, double puntos) {
        this.nombre = new SimpleStringProperty(nombre);
        this.puntos = new SimpleDoubleProperty(puntos);
    }
 
    public String getNombre() {
        return nombre.get();
    }

    public double getLastName() {
        return puntos.get();
    }   
        
}