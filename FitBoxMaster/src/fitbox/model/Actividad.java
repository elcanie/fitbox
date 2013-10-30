/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.model;

import java.util.LinkedList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author RUBEN
 */
public class Actividad {
    /*
     * Consultas
     */

//    public static final String INSERT_ACTIVIDAD = "INSERT INTO  `fitbox`.`usuario` (`idActividad` ,`nombre` ,`password`) VALUES (NULL, ";
    public static final String TODOS_ACTIVIDADESbyCATEGORIA = "select * from `actividad` where `categoria`=?";
    public static final String ENCONTRAR_ACTIVIDADporNOMBRE = "select * from actividad where nombre='?'";
    public static final String ENCONTRAR_ACTIVIDADporID = "select * from actividad where id = ? ";
    public static final String TODOS_ACTIVIDADES = "select * from `actividad`";

    /*
     * Atributos
     */
    public static final int NUMERO_ATRIBUTOS = 8;
    private final SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private final SimpleStringProperty nombre = new SimpleStringProperty("");
    private final SimpleStringProperty descripcion = new SimpleStringProperty("");
    private final SimpleDoubleProperty factor = new SimpleDoubleProperty(0);
    private final SimpleStringProperty video = new SimpleStringProperty("");
    private final SimpleStringProperty categoria = new SimpleStringProperty("");
    private final SimpleStringProperty objetivo = new SimpleStringProperty("");
     private final SimpleStringProperty nombreImagen = new SimpleStringProperty("");
    private String valores[] = new String[NUMERO_ATRIBUTOS];

    public Actividad() {
        this(new Integer(0), "", "", new Double(3.0), "", "", "","");

    }

    public Actividad(LinkedList array) {
        this((int) array.get(0), (String) array.get(1), (String) array.get(2), (double) array.get(3), (String) array.get(4), (String) array.get(5), (String) array.get(6),(String)array.get(7));
    }

    public Actividad(int id, String nombre, String descripcion, double factor, String video, String categoria, String objetivo,String nombreImagen) {
        setValores(id, nombre, descripcion, factor, video, categoria, objetivo,nombreImagen);
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setFactor(factor);
        setVideo(video);
        setCategoria(categoria);
        setObjetivo(objetivo);
        setNombreImagen(nombreImagen);

    }

    public double getFactor() {
        return factor.get();
    }

    /**
     *
     * @param fName
     */
    public void setFactor(double fName) {
        this.factor.set(fName);
    }

    public String getCategoria() {
        return categoria.get();
    }

    public void setCategoria(String fName) {
        this.categoria.set(fName);
    }
     public String getNombreImagen() {
        return nombreImagen.get();
    }

    public void setNombreImagen(String fName) {
        this.nombreImagen.set(fName);
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

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String fName) {
        descripcion.set(fName);
    }

    public String getVideo() {
        return video.get();
    }

    public void setVideo(String fName) {
        this.video.set(fName);
    }

    public String getObjetivo() {
        return objetivo.get();
    }

    public void setObjetivo(String fName) {
        this.objetivo.set(fName);
    }

    @Override
    public String toString() {
        return nombre.get();
    }

    private void setValores(int id, String nombre, String descripcion, double factor, String video, String categoria, String objetivo,String nombreImagen) {
        valores[0] = id + "";
        valores[1] = nombre + "";
        valores[2] = descripcion + "";
        valores[3] = factor + "";
        valores[4] = video + "";
        valores[5] = categoria + "";
        valores[6] = objetivo + "";
        valores[7]=nombreImagen+"";

    }

    public String[] getValores() {
        return valores;
    }
}
