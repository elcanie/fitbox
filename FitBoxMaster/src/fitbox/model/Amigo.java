/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.model;

import static fitbox.model.Jugador.NUMERO_ATRIBUTOS;
import java.util.LinkedList;

/**
 *
 * @author RUBEN
 */
public class Amigo {
    private int idJugador;
    private int idAmigo;
    public static final int NUMERO_ATRIBUTOS = 2;
    private Object valores[] = new Object[NUMERO_ATRIBUTOS];
    public static final String JugadorID = "select * from amigo j WHERE j.idJugador = ?";
    public static final String INSERT_AMIGO = " INSERT INTO  `amigo` (`idJugador` ,`idAmigo`) VALUES ( ";

    /**
     * @return the idJugador
     */
       public Amigo(LinkedList array) {
        this((int) array.get(0), (int) array.get(1));
    }
      public Amigo(int idJugador,int idAmigo) {
        
        setValores(idJugador, idAmigo);
        setIdJugador(idJugador);
        setIdAmigo(idAmigo);
    

    }
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
     * @return the idAmigo
     */
    public int getIdAmigo() {
        return idAmigo;
    }

    /**
     * @param idAmigo the idAmigo to set
     */
    public void setIdAmigo(int idAmigo) {
        this.idAmigo = idAmigo;
    }
    public void setValores(int idJugador,int idAmigo){
        valores[0]=idJugador+"";
        valores[1]=idAmigo+"";
    }
    public Object[] getValores(){
        return valores;
    }
}
