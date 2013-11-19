/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.model;

import fitbox.controller.dao.Dal;
import java.util.LinkedList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author RUBEN
 */
public class Desafio {

    public static final String INSERT_DESAFIO = " INSERT INTO  `desafio` (`nombre` ,`fechaInicio` ,`fechaFin` ,`estado`,`idUsuario` ,`idRival`, `idActividad`) VALUES ( ";
    /*
     * Atributos
     */
    public static final int NUMERO_ATRIBUTOS = 8;
    private final SimpleIntegerProperty idDesafio = new SimpleIntegerProperty(0);
    private final SimpleStringProperty nombre = new SimpleStringProperty("");
    private final SimpleStringProperty fechaInicio = new SimpleStringProperty("");
    private final SimpleStringProperty fechaFin = new SimpleStringProperty("");
    private final SimpleIntegerProperty estado = new SimpleIntegerProperty(0);
    private Jugador jugador = null;
    private Jugador rival = null;
    private Actividad actividad = null;
    public static final String desafioPorId = "select * from `desafio` j WHERE j.idRival = ? and estado=0";
    public static final String UPDATE_DESAFIO = "UPDATE `desafio` c SET `idDesafio`= ? ,`nombre`= ? ,`fechaInicio`= ? ,`fechaFin`= ? ,`estado`= ? ,`idUsuario`=?, `idRival`= ? ,`idActividad`= ? ";
    public static final String desafioPorIdDesafio = "select * from desafio j WHERE j.idDesafio = ?";
    private String valores[] = new String[NUMERO_ATRIBUTOS];

    public Desafio() {
        this(new Integer(0), "", "", "", 0, -1, -1,-1);

    }

    public Desafio(LinkedList array) {
        this((int) array.get(0), (String) array.get(1), (String) array.get(2), (String) array.get(3), (int) array.get(4), (int) array.get(5), (int) array.get(6), (int) array.get(7));
    }

    public Desafio(int id, String nombre, String fechaInicio, String fechaFin, int estado, int idJugador, int idRival, int idActividad) {
        if (idJugador != -1 && idRival != -1 && idActividad != -1) {
            Dal dal = Dal.getDal();
            List<Jugador> usuarios = dal.find(Jugador.JUGADORBYUSUARIO, new Object[]{idJugador}, Jugador.class);

            List<Jugador> usuarios2 = dal.find(Jugador.JUGADORBYUSUARIO, new Object[]{idRival}, Jugador.class);

            List<Actividad> actividades = dal.find(Actividad.ENCONTRAR_ACTIVIDADporID, new Object[]{idActividad}, Actividad.class);
            setJugador(usuarios.get(0));
            setRival(usuarios2.get(0));
            setActividad(actividades.get(0));
        }
        setValores(id, nombre, fechaInicio, fechaFin, estado, getJugador(), getRival(), getActividad());
        setId(id);
        setNombre(nombre);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setEstado(estado);
        



    }
        public Desafio(int id, String nombre, String fechaInicio, String fechaFin, int estado, Jugador jugador, Jugador rival, Actividad actividad) {
            System.out.println(jugador.getId());
        setValores(id, nombre, fechaInicio, fechaFin, estado,jugador, rival, actividad);
        setId(id);
        setNombre(nombre);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setEstado(estado);
        setJugador(jugador);
        setRival(rival);
        setActividad(actividad);
            }

    /**
     *
     * @param fName
     */
    public String getFechaInicio() {
        return fechaInicio.get();
    }

    public void setFechaInicio(String fName) {
        this.fechaInicio.set(fName);
    }

    public int getEstado() {
        return estado.get();
    }

    public void setEstado(int fName) {
        this.estado.set(fName);
        setValores(idDesafio.get(), nombre.get(), fechaInicio.get(), fechaFin.get(), estado.get(),jugador, rival, actividad);
    }

    public String getFechaFin() {
        return fechaFin.get();
    }

    public void setFechaFin(String fName) {
        this.fechaFin.set(fName);
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador fName) {
        this.jugador = fName;
    }

    public Jugador getRival() {
        return rival;
    }

    public void setRival(Jugador fName) {
        this.rival = fName;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad fName) {
        this.actividad = fName;
    }

    public int getId() {
        return idDesafio.get();
    }

    /**
     *
     * @param fName
     */
    public void setId(int fName) {
        idDesafio.set(fName);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String fName) {
        nombre.set(fName);
    }

    @Override
    public String toString() {
        return nombre.get();
    }

    private void setValores(int id, String nombre, String fechaInicio, String fechaFin, int estado, Jugador jugador, Jugador rival, Actividad actividad) {
        valores[0] = id + "";
        valores[1] = nombre + "";
        valores[2] = fechaInicio + "";
        valores[3] = fechaFin + "";
        valores[4] = estado + "";
        valores[5] = jugador.getId() + "";
        valores[6] = rival.getId() + "";
        valores[7] = actividad.getId() + "";


    }

    public String[] getValores() {
        return valores;
    }
}
