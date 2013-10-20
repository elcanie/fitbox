/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.model;

import static fitbox.model.Actividad.NUMERO_ATRIBUTOS;
import java.util.LinkedList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Marcos
 */
public class Video {
    public static final String VIDEObyUSUARIO = "select * from `video` where idUsuario=?";

    /*
     * Atributos
     */
    public static final int NUMERO_ATRIBUTOS = 7;
    private final SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private final SimpleStringProperty nombre = new SimpleStringProperty("");
    private final SimpleStringProperty URL = new SimpleStringProperty("");
   
   
    private String valores[] = new String[NUMERO_ATRIBUTOS];

    public Video() {
        this(new Integer(0), "", "");

    }

    public Video(LinkedList array) {
        this((int) array.get(0), (String) array.get(1), (String) array.get(2));
    }

    public Video(int id, String nombre, String URL){
        setValores(id, nombre, URL);
        setId(id);
        setNombre(nombre);
        setURL(URL);
        
    }

    public String getURL() {
        return URL.get();
    }

    /**
     *
     * @param fName
     */
        public int getId() {
        return id.get();
    }

    public void setId(int fName) {
        id.set(fName);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String fName) {
        nombre.set(fName);
    }


    public void setURL(String fName) {
        URL.set(fName);
    }
   

   

    @Override
    public String toString() {
        return nombre.get();
    }

    private void setValores(int id, String nombre, String URL) {
        valores[0] = id + "";
        valores[1] = nombre + "";
        valores[2] = URL + "";
        

    }

    public String[] getValores() {
        return valores;
    }
}
