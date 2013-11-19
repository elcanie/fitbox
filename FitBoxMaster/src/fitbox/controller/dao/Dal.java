
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller.dao;

import fitbox.model.Actividad;
import fitbox.model.Amigo;
import fitbox.model.Calendario;
import fitbox.model.Desafio;
import fitbox.model.Evento;
import fitbox.model.Jugador;
import fitbox.model.Noticia;
import fitbox.model.TablaActividad;
import fitbox.model.Usuario;
import fitbox.model.Video;
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
    
    public Dal(){
        daos =  new HashMap<String,Dao>();
        daos.put("Usuario", new Dao<Usuario>(Usuario.class));
        daos.put("Actividad",new Dao<Actividad>(Actividad.class));
        daos.put("Calendario",new Dao<Calendario>(Calendario.class));
        daos.put("Jugador", new Dao<Jugador>(Jugador.class));
       daos.put("Video", new Dao<Video>(Video.class));
       daos.put("Evento", new Dao<Evento>(Evento.class));
       daos.put("Noticia", new Dao<Noticia>(Noticia.class));
       daos.put("TablaActividad", new Dao<TablaActividad>(TablaActividad.class));
       daos.put("Desafio", new Dao<Desafio>(Desafio.class));
       daos.put("Amigo", new Dao<Amigo>(Amigo.class));
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
      public void updateRuben(Object objeto) {
       Dao dao = daos.get(objeto.getClass().getSimpleName());
       dao.updateRuben(objeto); 
    }
}

