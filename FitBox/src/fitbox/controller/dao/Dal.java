/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller.dao;

import fitbox.model.Actividad;
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
    }
    
    public static Dal getDal(){
    if(dal==null) dal = new Dal();
    return dal;
    }
    
    public List find(String consulta,Object[] parametros,Class tipoClase){
        Dao dao = daos.get(tipoClase.getSimpleName());
        return dao.find(consulta,parametros);
    }

    public void insert(Usuario usuario) {
       Dao dao = daos.get(usuario.getClass().getSimpleName());
       dao.insert(usuario); 
    }

}
