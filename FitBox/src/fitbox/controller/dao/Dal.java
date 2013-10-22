
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller.dao;

import fitbox.model.Actividad;
import fitbox.model.Calendario;
import fitbox.model.Jugador;
import fitbox.model.Usuario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Elias
 */
public class Dal {
    private static Dal dal;
    private static Map<String,Dao> daos;
    
    private Dal(){
        daos =  new HashMap<String,Dao>();
        daos.put("Usuario", new Dao<Usuario>(Usuario.class));
        daos.put("Actividad",new Dao<Actividad>(Actividad.class));
        daos.put("Calendario",new Dao<Calendario>(Calendario.class));
        daos.put("Jugador", new Dao<Jugador>(Jugador.class));
    }
    
    public static Dal getDal(){
    if(dal==null) dal = new Dal();
    return dal;
    }
    
    public List find(String consulta,Object[] parametros,Class tipoClase){
        Dao dao = daos.get(tipoClase.getSimpleName());
        return dao.find(consulta,parametros);
    }

    public void insert(Object objeto) {
       Dao dao = daos.get(objeto.getClass().getSimpleName());
       dao.insert(objeto); 
    }
    
    public void insertConId(Object objeto) {
       Dao dao = daos.get(objeto.getClass().getSimpleName());
       dao.insertConId(objeto); 
    }
    
    public void update(Object objeto) {
       Dao dao = daos.get(objeto.getClass().getSimpleName());
       dao.update(objeto); 
    }
}

