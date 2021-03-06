
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.model;

import static fitbox.model.Calendario.NUMERO_ATRIBUTOS;
import java.util.LinkedList;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Jose
 */
public class Jugador {

    private int id;
    private String apellidos;
    private String genero;
    private double altura;
    private double peso;
    private String correo;
    private Integer plan;
    private Integer calendario;
    private double puntos;
    private String nacimiento;
    public static final int NUMERO_ATRIBUTOS = 10;
    private Object valores[] = new Object[NUMERO_ATRIBUTOS];
    public static final String INSERT_JUGADOR = " INSERT INTO  `jugador` (`id` ,`apellidos` ,`genero` ,`altura` ,`peso` ,`correo`, `puntos` ,`plan` ,`calendario`,`nacimiento`) VALUES ( ";
    public static final String JUGADORBYUSUARIO = "select * from jugador j WHERE j.id = ?";
    public static final String UPDATE_JUGADOR = "UPDATE jugador c SET `id`= ? ,`apellidos`= ? ,`genero`= ? ,`altura`= ? ,`peso`= ? ,`correo`=?, `puntos`= ? ,`plan`= ? ,`calendario`=? , `nacimiento`=? WHERE c.id = ? ";
    public static final String TODOS_JUGADOES = "select * from `jugador`";

    public Jugador(LinkedList array) {
        this((int) array.get(0), (String) array.get(1), (String) array.get(2), (double) array.get(3), (double) array.get(4), (String) array.get(5), (double) array.get(6), (Integer) array.get(7), (Integer) array.get(8), (String)array.get(9));
    }

    public Jugador(int id, String apellidos, String genero, double altura, double peso, String correo, double puntos, Integer plan, Integer calendario,String nacimiento) {
        setValores(id, apellidos, genero, altura, peso, correo, puntos, plan, calendario,nacimiento);
        setId(id);
        setApellidos(apellidos);
        setGenero(genero);
        setAltura(altura);
        setPeso(peso);
        setCorreo(correo);
        setPlan(plan);
        setCalendario(calendario);
        setPuntos(puntos);
setNacimiento(nacimiento);
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
         valores[0] = id;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
        valores[1] = apellidos;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
        valores[2] = genero;
    }

    /**
     * @return the altura
     */
    public double getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(double altura) {
        this.altura = altura;
        valores[3] = altura;
    }

    /**
     * @return the peso
     */
    public double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(double peso) {
        this.peso = peso;
        valores[4] = peso;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
        valores[5] = correo;
    }

    /**
     * @return the plan
     */
    public Integer getPlan() {
        return plan;
    }

    /**
     * @param plan the plan to set
     */
    public void setPlan(Integer plan) {
        this.plan = plan;
        valores[7] = plan;
    }

    /**
     * @return the calendario
     */
    public Integer getCalendario() {
        return calendario;
    }

    /**
     * @param calendario the calendario to set
     */
    public void setCalendario(Integer calendario) {
        this.calendario = calendario;
    }

    /**
     * @return the puntos
     */
    public double getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(double puntos) {
        this.puntos = puntos;
    }

    /**
     * @return the valores
     */
    public Object[] getValores() {
        return valores;
    }

    /**
     * @param valores the valores to set
     */
    public void setValores(Object[] valores) {
        this.valores = valores;
    }

    private void setValores(int id, String apellidos, String genero, double altura, double peso, String correo, double puntos, Integer plan, Integer calendario,String nacimiento) {
        valores[0] = id;
        valores[1] = apellidos;
        valores[2] = genero;
        valores[3] = altura;
        valores[4] = peso;
        valores[5] = correo;
        valores[6] = puntos;
        valores[7] = plan;
        valores[8] = calendario;
        valores[9] = nacimiento;

    }

    /**
     * @return the nacimiento
     */
    public String getNacimiento() {
        return nacimiento;
    }

    /**
     * @param nacimiento the nacimiento to set
     */
    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
        valores[9]=nacimiento;
    }
}

