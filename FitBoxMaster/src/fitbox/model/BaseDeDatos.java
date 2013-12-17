/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.model;

import com.mysql.jdbc.PreparedStatement;
import fitbox.controller.dao.Conexion;
import fitbox.controller.dao.Dal;
import java.util.Collection;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Elias
 */
public class BaseDeDatos {
    List<Usuario> usuariosBD;
    List<Desafio> desafiosCreadosPorMiBD,desafiosDondeSoyRivalBD;
    List<Jugador> jugadoresBD;
    List<Actividad> actividadesBD;
    List<Evento> eventosUsuarioIniciadoBD;
    List<Calendario> CALENDARIOSPORAÑODIAYJUGADOR;
    private static BaseDeDatos bD;
    private List<Noticia> misNoticias;
    
    public static BaseDeDatos getBD(){
    if(bD==null) bD = new BaseDeDatos();
    return bD;
    }
    private List<TablaActividad> tablaActividad;
    
    
    private BaseDeDatos(){
    }
    
    public List<Usuario> getUsuarios(){
    if(usuariosBD==null)usuariosBD = Dal.getDal().find(Usuario.TODOS_USUARIOS, new Object[]{}, Usuario.class);
    return usuariosBD;
    }
    
    public List<Jugador> getJugadores(){
    if(jugadoresBD==null)jugadoresBD = Dal.getDal().find(Jugador.TODOS_JUGADOES, new Object[]{}, Jugador.class);
    return jugadoresBD;
    }
    
    public List<Evento> getEventosUsuarioIniciado(Usuario user){
        if(eventosUsuarioIniciadoBD==null)eventosUsuarioIniciadoBD = Dal.getDal().find(Evento.TODOS_EVENTOS_USUARIOINICIADO,new Object[]{user.getId()},Evento.class);
        return eventosUsuarioIniciadoBD;
    }
    
    public List<Actividad> getActividades(){
    if(actividadesBD==null) actividadesBD = Dal.getDal().find(Actividad.TODOS_ACTIVIDADES, new Object[]{}, Actividad.class);
    return actividadesBD;
    }
    
    public Usuario getUsuarioByPassANDName(String name,String pass){
    for(Usuario usuario : getUsuarios())
        if(usuario.getPassword().equals(pass) && usuario.getNombre().equalsIgnoreCase(name))
            return usuario;
    return null;
    }

    public List<Noticia> getNoticias() {
        if(misNoticias==null) misNoticias = Dal.getDal().find(Noticia.TODAS_NOTICIAS,new Object[]{},Noticia.class);
    return misNoticias;
    }

    public Jugador getJugador(int id) {
for(Jugador jugador : getJugadores())
        if(jugador.getId()==id)
            return jugador;
    return null;    }
    
    public List<Calendario> getCALENDARIOSPORAÑODIAYJUGADOR(String anyo,int id){
    if(CALENDARIOSPORAÑODIAYJUGADOR==null) CALENDARIOSPORAÑODIAYJUGADOR = Dal.getDal().find(Calendario.CALENDARIOSPORAÑODIAYJUGADOR, new Object[]{anyo,id}, Calendario.class);
    return CALENDARIOSPORAÑODIAYJUGADOR;
    }
    
   
    
    public List<TablaActividad> getTablaActividadesUsuario(int id){
    if(tablaActividad==null)tablaActividad = Dal.getDal().find(TablaActividad.Actividades_Usuario, new Object[]{id}, TablaActividad.class);
   return tablaActividad;
    }

    public List<Desafio> getDesafiosCreadosPorMi(int id) {
        if(desafiosCreadosPorMiBD==null)desafiosCreadosPorMiBD = Dal.getDal().find(Desafio.desafioPorIdCreadosPorMi, new Object[]{id}, Desafio.class);
    return desafiosCreadosPorMiBD;
    }
    public List<Desafio> getdesafiosDondeSoyRivalBD(int id) {
        if(desafiosDondeSoyRivalBD==null){desafiosDondeSoyRivalBD = Dal.getDal().find(Desafio.desafioPorIdDondeSoyRival, new Object[]{id}, Desafio.class);
        for(Desafio d : desafiosDondeSoyRivalBD)d.alReves = true;
        
        }return desafiosDondeSoyRivalBD;
    }

    public ObservableList<Ranking> getPuntuacionesAmigos(int user) {
        String sql = "SELECT U.nombre, J.puntos FROM jugador J, usuario U WHERE U.id = J.id AND J.id IN (SELECT idAmigo FROM amigo WHERE IdJugador = "+user+")ORDER BY J.puntos desc";
        return Dal.getDal().getPuntuacionesAmigos(sql);
       
        
    }
    
    
    
}
