/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import com.mysql.jdbc.PreparedStatement;
import fitbox.controller.dao.Conexion;
import fitbox.controller.dao.Dal;
import fitbox.model.Evento;
import fitbox.model.Usuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.net.URL;
import java.sql.Array;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import jfx.messagebox.MessageBox;

/**
 *
 * @author Alejandro
 */
public class RealizarEventosController implements Initializable, ControlledScreen {

    private ScreensController myController;

    @FXML
    private Button botonGO;
    @FXML
    private TableView tablaEventos;
    @FXML
    private ListView listaPuntuacion;
    @FXML
    private TableColumn nombreT, descripcionT, fechaT;

    private Usuario user;
    private Recurso recurso;
    private Conexion conexion;
    private Connection conectar;
    int idEventoSeleccionado;
    

    public void mostrarPuntuaciones(int id) {
        String consulta1 = "select * from puntuacion_evento where idEvento=" + id + ";";
        int idJug;

        try {

            Statement s = conectar.createStatement();
            Statement s1 = conectar.createStatement();

            ResultSet rs = s.executeQuery(consulta1);
            Collection<String> puntuaciones = new ArrayList();

            while (rs.next()) {
                //Cojo el id del jugador y su puntuacion
                idJug = rs.getInt("idJugador");
                Double punt = rs.getDouble("puntuacion");
                //Busco el nombre del jugador
                String consulta2 = "select nombre from usuario where id=" + idJug + ";";
                ResultSet rs1 = s1.executeQuery(consulta2);
                rs1.next();
                String nombre = rs1.getString("nombre");
                //Junto en una cadena el nombre y su puntuacion
                String cadena = nombre + "\t" + punt;
                //Añado a la lista
                puntuaciones.add(cadena);
            }

            ObservableList<String> p = FXCollections.observableArrayList(puntuaciones);
            listaPuntuacion.setItems(p);

        } catch (SQLException ex) {
            System.out.println("Fallo en mostrar puntuaciones (RealizarEventoController)");
            ex.printStackTrace();
        }
    }

    public void mostrarEventos() {
        //los eventos salen repetidos
        Dal dal = Dal.getDal();
        List<Evento> eventos = dal.find(Evento.TODOS_EVENTOS_USUARIOINICIADO, new Object[]{user.getId()}, Evento.class);
        ObservableList<Evento> datos = FXCollections.observableArrayList(eventos);

        for (int i = 0; i < datos.size(); i++) {
            int n = datos.get(i).getId();
            for (int j = 0; j < datos.size(); j++) {
                if (datos.get(j).getId() == n && i != j) {
                    datos.remove(j);
                }
            }
        }

        tablaEventos.setItems(datos);
    }

    @FXML
    private void handleButtonGO(ActionEvent event) {
        try {
            String consulta3 = "select * from puntuacion_evento where idEvento=" + idEventoSeleccionado + " and idJugador=" + user.getId() + ";";
            Statement s = conectar.createStatement();
            ResultSet rs = s.executeQuery(consulta3);
            if (!rs.next()) {
                System.out.println("Puedes participar");
                int[] identificadores = new int[2];
                identificadores[0] = user.getId();
                identificadores[1] = idEventoSeleccionado;
                recurso.putObject("puntuaciones", identificadores);
                recurso.putObject("conexionn", conexion);
                recurso.putObject("connectionn", conectar);
                myController.loadScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD, ScreensFramework.PANTALLA_REALIZARACTIVIDAD_FXML, recurso);
                //myController.setScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD);
            } else {
                MessageBox.show(ScreensFramework.stage,
                        "Ya has participado en este evento.",
                        "Information dialog",
                        MessageBox.ICON_INFORMATION | MessageBox.OK);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RealizarEventosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void mouseClicked(MouseEvent t) {
        idEventoSeleccionado = ((Evento) ((TableView) t.getSource()).getItems().get(((TableView) t.getSource()).getSelectionModel().getSelectedIndex())).getId();
        mostrarPuntuaciones(idEventoSeleccionado);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.recurso = (Recurso) rb;
        this.user = (Usuario) recurso.getObject("usuario");

        conexion = new Conexion();
        conectar = conexion.conectar1();

        nombreT.setCellValueFactory(new PropertyValueFactory<Evento, String>("nombre"));
        descripcionT.setCellValueFactory(new PropertyValueFactory<Evento, String>("descripcion"));
        fechaT.setCellValueFactory(new PropertyValueFactory<Evento, String>("fecha"));
        ScreensFramework.stage.setWidth(732);
        ScreensFramework.stage.setHeight(425);

        mostrarEventos();
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

}
