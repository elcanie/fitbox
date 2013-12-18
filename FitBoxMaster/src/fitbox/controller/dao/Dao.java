
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller.dao;

import com.mysql.jdbc.PreparedStatement;
import fitbox.model.Ranking;
import fitbox.model.Usuario;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    
    public Dao() {
        conexion = Conexion.getConexion();
    }

    
    public List<T> find(String consulta, Object[] parametros) {

        PreparedStatement s = (PreparedStatement) conexion.createStatement(consulta);
        int i = 1;
        for (Object o : parametros) {
            try {
                s.setObject(i, o);
                i++;
            } catch (SQLException ex) {
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ResultSet rs;
        List<T> datos = null;
        try {
            rs = s.executeQuery();
            datos = recorrerResultSet(rs);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }

    private List<T> recorrerResultSet(ResultSet _rs) {
        List<T> datos = new LinkedList<>();
        try {
            Integer a = claseT.getDeclaredField("NUMERO_ATRIBUTOS").getInt(null);
            while (_rs.next()) {
                Integer i = 0;
                LinkedList<Object> array = new LinkedList<Object>();
                while (++i <= a) {
                    array.add(_rs.getObject(i));
                    System.out.print(_rs.getObject(i) + "||");
                }
                System.out.println("");
                T instanciaT = null;
                Constructor con = claseT.getConstructor(LinkedList.class);
                instanciaT = (T) con.newInstance(array);
                datos.add(instanciaT);
            }
        } catch (SecurityException | SQLException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }

    public void insert(T t) {
        try {
            Statement s = conexion.createStatement();
            Object valores[] = (Object[]) (claseT.getMethod("getValores", null)).invoke(t, null);
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
         public void updateRuben(T t) {

        try {
            Object valores[] = (Object[]) (claseT.getMethod("getValores", null)).invoke(t, null);
            String consulta = null;
            consulta = (String) claseT.getDeclaredField("UPDATE_" + claseT.getSimpleName().toUpperCase()).get(null);
            PreparedStatement s = (PreparedStatement) conexion.createStatement(consulta);
            int i = 1;
            for (Object o : valores) {
                try {
                    System.out.println(i+" "+o.toString());
                    s.setObject(i, o);
                    i++;
                } catch (SQLException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            try {
              //  s.setObject(i, valores[0]);
               // System.out.println(i+" "+valores[0]);
                Logger.getLogger("GenericDAO").info("Update: " + consulta);
                s.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertConId(T t) {
        try {
            Statement s = conexion.createStatement();
            Object valores[] = (Object[]) (claseT.getMethod("getValores", null)).invoke(t, null);
            String consulta = null;
            consulta = (String) claseT.getDeclaredField("INSERT_" + claseT.getSimpleName().toUpperCase()).get(null);

            for (int i = 0; i < valores.length; i++) {
                consulta += "'" + valores[i] + "'";
                consulta += i < valores.length - 1 ? "," : ")";
            }

            Logger.getLogger("GenericDAO").info("Insertar: " + consulta);
            s.executeUpdate(consulta);

        } catch (SQLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(T t) {

        try {
            Object valores[] = (Object[]) (claseT.getMethod("getValores", null)).invoke(t, null);
            String consulta = null;
            consulta = (String) claseT.getDeclaredField("UPDATE_" + claseT.getSimpleName().toUpperCase()).get(null);
            PreparedStatement s = (PreparedStatement) conexion.createStatement(consulta);
            int i = 1;
            for (Object o : valores) {
                try {
                    if (o != null) {
                        System.out.println(i + " " + o.toString());
                    }
                    s.setObject(i, o);
                    i++;
                } catch (SQLException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                s.setObject(i, valores[0]);
                System.out.println(i + " " + valores[0]);
                Logger.getLogger("GenericDAO").info("Update: " + consulta);
                s.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Ranking> getPuntuacionesAmigos(String consulta) {

        PreparedStatement s = (PreparedStatement) conexion.createStatement(consulta);
        
        
        ResultSet rs;
        List<Ranking> datos = new LinkedList<>();
        try {
            rs = s.executeQuery();
            
            while(rs.next()){
            Ranking rk = new Ranking(rs.getString(1),rs.getDouble(2));
            datos.add(rk);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Ranking> datosRanking = FXCollections.observableArrayList(datos);
        return datosRanking;
    }
}
