/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller.dao;

import fitbox.model.Usuario;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
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
public class Dao<T extends Object> {

    protected Conexion conexion;
    protected Class<T> claseT;

    public Dao(Class<T> claseT) {
        this.claseT = claseT;
        conexion = Conexion.getConexion();
    }

    public Collection<T> find(String consulta) {

        Statement s = conexion.createStatement();
        ResultSet rs;
        Collection<T> datos = null;
        try {
            rs = s.executeQuery(consulta);
            datos = recorrerResultSet(rs);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }

    private Collection<T> recorrerResultSet(ResultSet _rs) {
        Collection<T> datos = new LinkedList<>();
        try {
            Integer a = claseT.getDeclaredField("NUMERO_ATRIBUTOS").getInt(null);
            System.out.println(a);
            while(_rs.next()){
                Integer i = 0;
                LinkedList<Object> array = new LinkedList<Object>();
                while (++i <= a) {
                    array.add(_rs.getObject(i));
                }
                T instanciaT = null;
                instanciaT = (T) claseT.getConstructor(LinkedList.class).newInstance(array);
                datos.add(instanciaT);
            }
        } catch (SecurityException | SQLException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException |  InvocationTargetException | NoSuchMethodException | InstantiationException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return datos;
    }

    public void insert(T t) {
        try {
            Statement s = conexion.createStatement();
            Collection<T> datos = null;
            String valores[] = (String[]) (claseT.getMethod("getValores", null)).invoke(t, null);
            String consulta = null;
            consulta = (String) claseT.getDeclaredField("INSERT_" + claseT.getSimpleName().toUpperCase()).get(null);

            for (int i = 1; i < valores.length; i++) {
                consulta += "'" + valores[i] + "'";
                consulta += i < valores.length - 1 ? "," : ")";
            }

            Logger.getLogger("GenericDAO").info("Insertar: " + consulta);
            s.executeUpdate(consulta);

        } catch (SQLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
