/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.model;

import static fitbox.model.Evento.NUMERO_ATRIBUTOS;
import java.util.LinkedList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Marcos
 */
public class Noticia {
     /*
     * Consultas
     */

        public static final String TODAS_NOTICIAS = "select * from `noticias`";

    /*
     * Atributos
     */
    public static final int NUMERO_ATRIBUTOS = 2;
    private final SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private final SimpleStringProperty descripcion = new SimpleStringProperty("");
    private String valores[] = new String[NUMERO_ATRIBUTOS];

    public Noticia() {
        this(new Integer(0), "");

    }

    public Noticia(LinkedList array) {
        this((int) array.get(0), (String) array.get(1));
    }

    public Noticia(int id, String descripcion) {
        setValores(id,descripcion);
        setId(id);
        setDescripcion(descripcion);
        

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

   
    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String fName) {
        descripcion.set(fName);
    }
    
    
   

    @Override
    public String toString() {
         return descripcion.get();
    }

    private void setValores(int id, String descripcion) {
        valores[0] = id + "";
        valores[1] = descripcion + "";


    }

    public String[] getValores() {
        return valores;
    }
}
