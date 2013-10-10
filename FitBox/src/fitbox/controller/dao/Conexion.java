package fitbox.controller.dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private static Conexion conexion;
    private Connection connection;

    private Conexion() {
        conectar();
    }

    public static Conexion getConexion() {
        if (conexion == null) {
            conexion = new Conexion();
        }
        return conexion;
    }

    private void conectar() {
        try {
            Logger.getLogger("Conexion").info("Intentando cargar el conector...");
            Class.forName("com.mysql.jdbc.Driver");

            //Intentamos conectarnos a la base de Datos en este caso una base
            Logger.getLogger("Conexion").info("Conectando a la base...");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/fitbox", "root", "");
            Logger.getLogger("Conexion").info("Conexion a BD establecida");


        } catch (SQLException ex) {
            System.out.println("Error de mysql");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se produjo un error inesperado: " + e.getMessage());
        }
    }

    PreparedStatement createStatement(String consulta) {
        try {
            return connection.prepareStatement(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     Statement createStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
