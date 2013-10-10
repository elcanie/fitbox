/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox;

import fitbox.controller.dao.Dal;
import fitbox.controller.dao.Dao;
import fitbox.model.Usuario;
import java.util.Collection;

/**
 *
 * @author Elias
 */
public class MuestraUsoBaseDatos {
    
    public static void main (String []args){
        Dal dal = Dal.getDal();
       //Usuario usuario = new Usuario(0,"Name","Apl1");
       //dal.insert(usuario);
       Collection<Usuario> datos = dal.find(Usuario.USUARIOSBYID,new Object[]{1},Usuario.class);
       for(Usuario u : datos) System.out.println(u.getNombre());
        
    }
    
}
