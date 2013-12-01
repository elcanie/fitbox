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
public class Evento {
     /*
     * Consultas
     */

    public static final String TODOS_EVENTOS_USUARIOINICIADO = "SELECT e.* FROM eventos e, calendario c WHERE e.id = c.idEvento AND c.idJugador = ?";
    public static final String TODOS_EVENTOS = "select * from `eventos`";
    public static final String ENCONTRAR_EVENTOporID = "select * from eventos where id = ? ";
    /*
     * Atributos
     */
    public static final int NUMERO_ATRIBUTOS = 4;
    private final SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private final SimpleStringProperty nombre = new SimpleStringProperty("");
    private final SimpleStringProperty descripcion = new SimpleStringProperty("");
    private final SimpleStringProperty fecha = new SimpleStringProperty("");
    private String valores[] = new String[NUMERO_ATRIBUTOS];

    public Evento() {
        this(new Integer(0), "", "", "");

    }

    public Evento(LinkedList array) {
        this((int) array.get(0), (String) array.get(1), (String) array.get(2), (String) array.get(3));
    }

    public Evento(int id, String nombre, String descripcion, String fecha) {
        setValores(id, nombre, descripcion, fecha);
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setFecha(fecha);

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

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String fName) {
        descripcion.set(fName);
    }
    
    public String getFecha() {
        return fecha.get();
    }

    public void setFecha(String fName) {
        fecha.set(fName);
    }

   

    @Override
    public String toString() {
        String s = nombre.get()+"\t"+fecha.get()+"\t"+descripcion.get();
        return s;
    }

    private void setValores(int id, String nombre, String descripcion, String fecha) {
        valores[0] = id + "";
        valores[1] = nombre + "";
        valores[2] = descripcion + "";
        valores[3] = fecha + "";

    }

    public String[] getValores() {
        return valores;
    }
}
