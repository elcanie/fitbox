/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller.dao;

import fitbox.model.Usuario;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
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
    }
    
    public static Dal getDal(){
    if(dal==null) dal = new Dal();
    return dal;
    }
    
    public Collection find(String consulta,Class tipoClase){
        Dao dao = daos.get(tipoClase.getSimpleName());
        return dao.find(consulta);
    }

    public void insert(Usuario usuario) {
       Dao dao = daos.get(usuario.getClass().getSimpleName());
       dao.insert(usuario); 
    }

}
