/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Elias
 */
public class Usuario {

    private final SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private final SimpleStringProperty nombre = new SimpleStringProperty("");
    private final SimpleStringProperty password = new SimpleStringProperty("");

    public Usuario() {
        this(0, "", "");
    }
    
    public Usuario(Object[] array){
    this((int)array[0],(String)array[1],(String)array[2]);
    }

    public Usuario(int id, String nombre, String password) {
        setId(id);
        setNombre(nombre);
        setFactor(password);
    }

    public int getId() {
        return id.get();
    }

    /**
     *
     * @param fName
     */
    public void setId(int fName) {
        id.set(fName);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String fName) {
        nombre.set(fName);
    }

    public String getPassword() {
        return password.get();
    }

    public void setFactor(String fName) {
        password.set(fName);
    }
     @Override public String toString() {
     return nombre.get();
     }
}
