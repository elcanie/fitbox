/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox;

import fitbox.controller.dao.Dal;
import fitbox.controller.dao.Dao;
import fitbox.model.Calendario;
import fitbox.model.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Elias
 */
public class MuestraUsoBaseDatos {
    
    public static void main (String []args){
        Dal dal = Dal.getDal();
       //Usuario usuario = new Usuario(0,"Name","Apl1");
       //dal.insert(usuario);
       //Collection<Usuario> datos = dal.find(Usuario.USUARIOSBYID,new Object[]{1},Usuario.class);
       //for(Usuario u : datos) System.out.println(u.getNombre());
        List<Calendario> calendarios = dal.find(Calendario.TODOS_CALENDARIOS, new Object[]{}, Calendario.class);
        for(Calendario cal : calendarios)
            System.out.println(cal.getFecha().getDayOfMonth());
    }
    
}
