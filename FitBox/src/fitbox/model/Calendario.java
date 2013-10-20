/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Elias
 */
public class Calendario {
    /*
     * Consultas
     */
    public static final String INSERT_CALENDARIO = "INSERT INTO  `fitbox`.`calendario` (`id` ,`fecha` ,`idActividad`,`estadoActividad`,`idJugador`) VALUES (NULL, ";
    public static final String TODOS_CALENDARIOS = "select * from calendario";
    public static final String CALENDARIOBYJUGADORID = "select * from calendario c WHERE c.idJugador = ?";
    public static final String CALENDARIOSPORAÑODIAYJUGADOR = "SELECT * FROM calendario c WHERE c.fecha LIKE ? AND c.idJugador = ?";
    /*
     * Atributos
     */
    public static final int NUMERO_ATRIBUTOS = 5;
    private int id;
    private String fechaStr;
    private DateTime fecha;
    private int idActividad;
    private int estadoActividad;
    private int idJugador;
    private Object valores[] = new Object[NUMERO_ATRIBUTOS];

    
    public Calendario(LinkedList array) {
        this((int) array.get(0), (String) array.get(1), (int) array.get(2), (int) array.get(3), (int) array.get(4));
    }

    public Calendario(int id, String fechaStr, int idActividad, int estadoActividad, int idJugador) {
        setValores(id,fechaStr,idActividad,estadoActividad,idJugador);
        setId(id);
        setIdActividad(idActividad);
        setEstadoActividad(estadoActividad);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
fecha = formatter.parseDateTime(fechaStr);
    }


    private void setValores(int id, String fecha, int idActividad, int estadoActividad, int idJugador) {
        valores[0] = id ;
        valores[1] = fecha;
        valores[2] = idActividad;
        valores[3] = estadoActividad;
        valores[4] = idJugador;
    }

    public Object[] getValores() {
        return valores;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the fecha
     */
    public DateTime getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(DateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the idActividad
     */
    public int getIdActividad() {
        return idActividad;
    }

    /**
     * @param idActividad the idActividad to set
     */
    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    /**
     * @return the estadoActividad
     */
    public int getEstadoActividad() {
        return estadoActividad;
    }

    /**
     * @param estadoActividad the estadoActividad to set
     */
    public void setEstadoActividad(int estadoActividad) {
        this.estadoActividad = estadoActividad;
    }

    /**
     * @return the idJugador
     */
    public int getIdJugador() {
        return idJugador;
    }

    /**
     * @param idJugador the idJugador to set
     */
    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    /**
     * @param valores the valores to set
     */
    public void setValores(Object[] valores) {
        this.valores = valores;
    }
    
    @Override
    public String toString(){
        if(getIdActividad()<9999) return "Actividades";
    return getFecha().getDayOfMonth()+"\n Nada";
    }
    
    public String toString2(){
       return (getFecha().getHourOfDay()+" "+getIdActividad());
    }
}
