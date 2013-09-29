/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.model;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Elias
 */
public class Usuario {
    /*
     * Consultas
     */
    public static final String INSERT_USUARIO = "INSERT INTO  `fitbox`.`usuario` (`id` ,`nombre` ,`password`) VALUES (NULL, ";
    public static final String TODOS_USUARIOS = "select * from usuario";
    /*
     * Atributos
     */
    public static final int NUMERO_ATRIBUTOS = 3;
    private final SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private final SimpleStringProperty nombre = new SimpleStringProperty("");
    private final SimpleStringProperty password = new SimpleStringProperty("");
    private String valores[] = new String[3];

    public Usuario() {
        this(new Integer(0), "", "");
    }

    public Usuario(LinkedList array) {
        this((int) array.get(0), (String) array.get(1), (String) array.get(2));
    }

    public Usuario(int id, String nombre, String password) {
        setValores(id, nombre, password);
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

    @Override
    public String toString() {
        return nombre.get();
    }

    private void setValores(int id, String nombre, String password) {
        valores[0] = id + "";
        valores[1] = nombre + "";
        valores[2] = password + "";
    }

    public String[] getValores() {
        return valores;
    }
}
