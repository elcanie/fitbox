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


/**
 *
 * @author RUBEN
 */
public class Actividad {
     /*
     * Consultas
     */
    public static final String INSERT_ACTIVIDAD = "INSERT INTO  `fitbox`.`usuario` (`idActividad` ,`nombre` ,`password`) VALUES (NULL, ";
    public static final String TODOS_ACTIVIDADESbyCATEGORIA = "select * from actividad where nombreCategoria='?'";
    public static final String ENCONTRAR_ACTIVIDADporNOMBRE="select * from actividad where nombre='?'";
    public static final String TODOS_ACTIVIDADES = "select * from `actividad`";

    /*
     * Atributos
     */
    public static final int NUMERO_ATRIBUTOS = 7;
    private final SimpleIntegerProperty id=new SimpleIntegerProperty(0);
    private final SimpleStringProperty nombre=new SimpleStringProperty("");
    private final SimpleStringProperty descripcion=new SimpleStringProperty("");
    private final SimpleIntegerProperty factor=new SimpleIntegerProperty(0);
    private final SimpleStringProperty video=new SimpleStringProperty("");
    private final SimpleStringProperty categoria=new SimpleStringProperty("");
    private final SimpleStringProperty objetivo=new SimpleStringProperty("");
    
    private String valores[] = new String[NUMERO_ATRIBUTOS];

    public Actividad() {
        this(new Integer(0), "", "",new Integer(0),"","","");
        
    }

    public Actividad(LinkedList array) {
        this((int) array.get(0), (String) array.get(1), (String) array.get(2),(int)array.get(3),(String)array.get(4),(String)array.get(5),(String)array.get(6));
    }

    public Actividad(int id, String nombre, String descripcion,int factor,String video,String categoria, String objetivo) {
        setValores(id, nombre, descripcion,factor,video,categoria,objetivo);
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setFactor(factor);
        setVideo(video);
        setCategoria(categoria);
        setObjetivo(objetivo);
 
    }
    public int getFactor(){
        return factor.get();
    }
    public void setFactor(int factor){
        this.factor.set(factor);
    }
    public String getCategoria(){
        return categoria.get();
    }
    public void setCategoria(String categoria){
        this.categoria.set(categoria);
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
    public String getVideo(){
        return video.get();
    }
    public String getObjetivo(){
        return objetivo.get();
    }
    
    public void setVideo(String urlVideo){
        this.video.set(urlVideo);
    }
    public void setObjetivo(String objetivo){
        this.objetivo.set(objetivo);
    }
    @Override
    public String toString() {
        return nombre.get();
    }

    private void setValores(int id, String nombre, String descripcion,int factor, String video,String categoria,String objetivo) {
        valores[0] = id + "";
        valores[1] = nombre + "";
        valores[2] = descripcion + "";
        valores[3]=factor+"";
        valores[4]=video+"";
        valores[5]=categoria+"";
        valores[5]=  objetivo+"";
        
    }

    public String[] getValores() {
        return valores;
    }

   
}
