
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
    public static final String INSERT_CALENDARIO = "INSERT INTO  `calendario` (`id` ,`fecha` ,`idActividad`,`estadoActividad`,`idJugador`) VALUES (NULL, ";
    public static final String TODOS_CALENDARIOS = "select * from `calendario`";
    public static final String CALENDARIOBYJUGADORID = "select * from `calendario`  WHERE idJugador = ?";
    public static final String CALENDARIOSPORAÑODIAYJUGADOR = "SELECT * FROM calendario c WHERE c.fecha LIKE ? AND c.idJugador = ?";
    public static final String CALENDARIOSPORDIAYJUGADOR = "SELECT * FROM calendario c WHERE c.fecha LIKE ? AND c.idJugador = ?";
    public static final String UPDATE_CALENDARIO = "UPDATE calendario c SET c.id = ? , c.fecha = ?, c.idActividad = ? , c.estadoActividad = ? , idJugador = ? WHERE c.id = ? ";
    /*
     * Atributos
     */
    public static final int NUMERO_ATRIBUTOS = 6;
    private Integer id;
    private DateTime fecha;
    private Integer idActividad;
    private Integer estadoActividad;
    private Integer evento;
    private Integer idJugador;
    private String hora;
    private Object valores[] = new Object[NUMERO_ATRIBUTOS];

    
    public Calendario(LinkedList array) {
        this((Integer) array.get(0), (String) array.get(1), (Integer) array.get(2), (Integer) array.get(3),(Integer) array.get(4), (Integer) array.get(5));
    }

    public Calendario(Integer id, String fechaStr, Integer idActividad,Integer evento, Integer estadoActividad, Integer idJugador) {
        setValores(id,fechaStr,idActividad,estadoActividad,idJugador);
        setId(id);
        setIdActividad(idActividad);
        setEvento(evento);
        setEstadoActividad(estadoActividad);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
        try{
        fecha = formatter.parseDateTime(fechaStr);
       }catch(java.lang.IllegalArgumentException e){
       formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
       fecha = formatter.parseDateTime(fechaStr);
       }
    }


    private void setValores(Integer id, String fecha, Integer idActividad, Integer estadoActividad, Integer idJugador) {
        valores[0] = id ;
        valores[1] = fecha;
        valores[2] = idActividad;
        valores[3] = evento;
        valores[4] = estadoActividad;
        valores[5] = idJugador;
    }

    public Object[] getValores() {
        return valores;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
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
    public Integer getIdActividad() {
        return idActividad;
    }

    /**
     * @param idActividad the idActividad to set
     */
    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    /**
     * @return the estadoActividad
     */
    public Integer getEstadoActividad() {
        return estadoActividad;
    }

    /**
     * @param estadoActividad the estadoActividad to set
     */
    public void setEstadoActividad(Integer estadoActividad) {
        this.estadoActividad = estadoActividad;
    }

    /**
     * @return the idJugador
     */
    public Integer getIdJugador() {
        return idJugador;
    }

    /**
     * @param idJugador the idJugador to set
     */
    public void setIdJugador(Integer idJugador) {
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
        if(getIdActividad()!= null && getIdActividad()<9999) return "Actividades";
        if(getEvento()!=null) return "Evento";
    return getFecha().getDayOfMonth()+"\n Nada";
    }
    
    public String toString2(){
       return "ok";//(getFecha().getHourOfDay()+" "+getIdActividad());
    }

    /**
     * @return the evento
     */
    public Integer getEvento() {
        return evento;
    }

    /**
     * @param evento the evento to set
     */
    public void setEvento(Integer evento) {
        this.evento = evento;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return fecha.getHourOfDay()+":"+fecha.getMinuteOfHour();
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }
}
