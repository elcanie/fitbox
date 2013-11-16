/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.model;


import java.util.LinkedList;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Marcos
 */
public class TablaActividad {
     /*
     * Consultas
     */

    public static final String Actividades_Usuario = "Select c.fecha, a.nombre FROM calendario c, actividad a Where c.idJugador = ? and c.idActividad = a.id";

    

    /*
     * Atributos
     */
    public static final int NUMERO_ATRIBUTOS = 2;
    private final SimpleStringProperty nombre = new SimpleStringProperty("");
    private final SimpleStringProperty fecha = new SimpleStringProperty("");
    private String valores[] = new String[NUMERO_ATRIBUTOS];

    public TablaActividad() {
        this("", "");

    }

    public TablaActividad(LinkedList array) {
        this((String) array.get(0), (String) array.get(1));
    }

    public TablaActividad(String fecha, String nombre) {
        setValores(fecha, nombre);
        setNombre(nombre);
        setFecha(fecha);

    }

 
    /**
     *
     * @param fName
     */

    


    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String fName) {
        nombre.set(fName);
    }

    
    public String getFecha() {
        return fecha.get();
    }

    public void setFecha(String fName) {
        fecha.set(fName);
    }

   

    @Override
    public String toString() {
        String s = nombre.get()+"\t"+fecha.get();
        return s;
    }

    private void setValores( String nombre, String fecha) {
        valores[0] = nombre + "";
        valores[1] = fecha + "";

    }

    public String[] getValores() {
        return valores;
    }
}


