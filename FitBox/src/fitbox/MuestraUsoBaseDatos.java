/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox;

import fitbox.controller.dao.Dal;
import fitbox.controller.dao.Dao;
import fitbox.model.Actividad;
import fitbox.model.Usuario;
import java.util.Collection;

/**
 *
 * @author Elias
 */
public class MuestraUsoBaseDatos {
    
    public static void main (String []args){
        Dal dal = Dal.getDal();
       Usuario usuario = new Usuario(0,"Name","Apl1");
       Usuario usuario2=new Usuario(1,"Name2","Apli2");
       dal.insert(usuario);
       dal.insert(usuario2);
       Collection<Usuario> datos = dal.find(Usuario.TODOS_USUARIOS,Usuario.class);
       //for(Usuario u : datos) System.out.println(u.getNombre());
        Collection<Actividad> datos2=dal.find(Actividad.TODOS_ACTIVIDADES, Actividad.class);
        for(Actividad a: datos2){
            System.out.println(a.getNombre());
        }
    }
    
}
