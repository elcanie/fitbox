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
    public static final int NUMERO_ATRIBUTOS = 10;
    private final SimpleIntegerProperty idDesafio = new SimpleIntegerProperty(0);
    private final SimpleStringProperty nombre = new SimpleStringProperty("");
    private final SimpleStringProperty fechaInicio = new SimpleStringProperty("");
    private final SimpleStringProperty fechaFin = new SimpleStringProperty("");
    private final SimpleIntegerProperty estado = new SimpleIntegerProperty(0);
    private Jugador jugador = null;
    private Jugador rival = null;
    private Actividad actividad = null;
    public static final String desafioPorIdDondeSoyRival = "select * from `desafio` j WHERE j.idRival = ?";
        public static final String desafioPorIdCreadosPorMi = "select * from `desafio` j WHERE j.idUsuario = ?";
    public static final String UPDATE_DESAFIO = "UPDATE `desafio` c SET idDesafio = ? , `nombre`= ? ,`fechaInicio`= ? ,`fechaFin`= ? ,`estado`= ? ,`idUsuario`=?, "
            + "`idRival`= ? ,`idActividad`= ?,`puntosUsuario` = ? , `puntosRival` = ? WHERE idDesafio = ?";
    public static final String desafioPorIdDesafio = "select * from desafio j WHERE j.idDesafio = ?";
    private String valores[] = new String[NUMERO_ATRIBUTOS];
    public boolean alReves=false;

    public Desafio() {
        this(new Integer(0), "", "", "", 0, -1, -1,-1,0,0);

    }

    public Desafio(LinkedList array) {
        this((int) array.get(0), (String) array.get(1), (String) array.get(2), (String) array.get(3), (int) array.get(4), 
                (int) array.get(5), (int) array.get(6), (int) array.get(7),(float) array.get(8), (float) array.get(9));
    }

    public Desafio(int id, String nombre, String fechaInicio, String fechaFin, int estado, int idJugador, int idRival, int idActividad,float puntosUsuario,float puntosRival) {
        if (idJugador != -1 && idRival != -1 && idActividad != -1) {
            Dal dal = Dal.getDal();
            Jugador j1 = BaseDeDatos.getBD().getJugador(idJugador);

            Jugador j2 = BaseDeDatos.getBD().getJugador(idRival);

            List<Actividad> actividades = dal.find(Actividad.ENCONTRAR_ACTIVIDADporID, new Object[]{idActividad}, Actividad.class);
            setJugador(j1);
            setRival(j2);
            setActividad(actividades.get(0));
        }
        setValores(id, nombre, fechaInicio, fechaFin, estado, getJugador(), getRival(), getActividad(),puntosUsuario,puntosRival);
        setId(id);
        setNombre(nombre);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setEstado(estado);
        



    }
//        public Desafio(int id, String nombre, String fechaInicio, String fechaFin, int estado, Jugador jugador, Jugador rival, Actividad actividad) {
//            System.out.println(jugador.getId());
//        setValores(id, nombre, fechaInicio, fechaFin, estado,jugador, 
//                rival, actividad,0,0);
//        setId(id);
//        setNombre(nombre);
//        setFechaInicio(fechaInicio);
//        setFechaFin(fechaFin);
//        setEstado(estado);
//        setJugador(jugador);
//        setRival(rival);
//        setActividad(actividad);
//            }

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
    valores[4]=fName+"";    
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

    private void setValores(int id, String nombre, String fechaInicio, String fechaFin, int estado, Jugador jugador, Jugador rival, 
            Actividad actividad,float puntosUsuario,float puntosRival) {
        valores[0] = id + "";
        valores[1] = nombre + "";
        valores[2] = fechaInicio + "";
        valores[3] = fechaFin + "";
        valores[4] = estado + "";
        valores[5] = jugador.getId() + "";
        valores[6] = rival.getId() + "";
        valores[7] = actividad.getId() + "";
        valores[8] = puntosUsuario+"";
        valores[9] = puntosRival+"";

    }

    public String[] getValores() {
        return valores;
    }
    
    public String getMisPuntos(){
        if(alReves) return valores[9];
    return valores[8];
    }
    
    public String getSusPuntos(){
        if(alReves) return valores[8];
    return valores[9];
    }
    
    
    
    public String getNombreRival(){
        if(alReves)return jugador.getApellidos();
    return rival.getApellidos();
    }
    
    public String getRealState(){
    switch(getEstado()){
        case 0:
            return "Sin realizar";
        case 1:
            return "Esperando";
        case 2:
            return "Pendiente";
        case 3:
            return "Acabado";
        case 4:
            return "Fuera de plazo";
    }
    return "Null";
    
    }
}
