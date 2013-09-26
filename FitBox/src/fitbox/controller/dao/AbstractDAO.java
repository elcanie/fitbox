/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller.dao;

import fitbox.model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elias
 */
public class AbstractDAO {
    
    protected Conexion conexion;
    
    public AbstractDAO(){
    conexion = Conexion.getConexion();
    }

    public Collection<Usuario> findAll(String consulta) {
        
        Statement s = conexion.createStatement();
        ResultSet rs;
        Collection<Usuario> datos = null;
        try {
            rs = s.executeQuery(consulta);
            datos = recorrerResultSet(rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }
    
    private Collection<Usuario> recorrerResultSet(ResultSet _rs) throws SQLException{
      Collection<Usuario> datos = new LinkedList<>();
      Integer parametros = Usuario.class.getDeclaredFields().length;
      while(_rs.next()){
          Integer i = 0;
          Object array[] = new Object[parametros];
          while(++i <= parametros){
          array[i-1]=(_rs.getObject(i));
                  }
          
                  datos.add(new Usuario(array));
      }
      return datos;
    }
}
