/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox;

import fitbox.controller.dao.AbstractDAO;
import fitbox.controller.dao.Consultas;
import fitbox.model.Usuario;
import java.util.Collection;

/**
 *
 * @author Elias
 */
public class MuestraUsoBaseDatos {
    
    public static void main (String []args){
        AbstractDAO a = new AbstractDAO();
        Collection<Usuario> datos = a.findAll(Consultas.TODOS_USUARIOS);
        for(Usuario u : datos) System.out.println(u.getNombre());
    }
    
}
