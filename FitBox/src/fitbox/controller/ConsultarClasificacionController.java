/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.controller.dao.Dal;
import fitbox.model.Jugador;
import fitbox.model.Ranking;
import fitbox.model.Usuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Jose
 */
public class ConsultarClasificacionController implements Initializable, ControlledScreen {

    private ScreensController myController;
    private List<Usuario> usuarios;
    private Recurso recurso;
    private List<Jugador> j;
    private Dal dal;
    @FXML
    private TableView tabla;

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }

    public void initialize(URL url, ResourceBundle rb) {
        ScreensFramework.stage.setWidth(386);
        ScreensFramework.stage.setHeight(337);
        this.recurso = (Recurso) rb;
        dal = Dal.getDal();
        List<Usuario> usuarios = dal.find(Usuario.TODOS_USUARIOS, new Object[]{}, Usuario.class);
        List<Ranking> rankingList = new LinkedList<>();
        for (Usuario u : usuarios) {
            Jugador _jugador = (Jugador) dal.find(Jugador.JUGADORBYUSUARIO, new Object[]{u.getId()}, Jugador.class).get(0);
            rankingList.add(new Ranking(u.getNombre(), _jugador.getPuntos()));
        }

        TableColumn nombreColumn = new TableColumn("Nombre");
        TableColumn puntosColumn = new TableColumn("Puntos");
        nombreColumn.setCellValueFactory(
                new PropertyValueFactory<Ranking, String>("nombre"));
        puntosColumn.setCellValueFactory(
                new PropertyValueFactory<Ranking, Double>("puntos"));
        
        nombreColumn.setMinWidth(150);
        puntosColumn.setMinWidth(50);
        tabla.getColumns().addAll(nombreColumn,puntosColumn);
          
         
         ObservableList<Ranking> datosRanking = FXCollections.observableArrayList(rankingList);
         tabla.setItems(datosRanking);


        //usuarios = (List<Usuario>) recurso.getObject("usuario");
        //j = (List<Jugador>)dal.find(Jugador.JUGADORBYUSUARIO, new Object[]{"*"}, Jugador.class).get(0);
        //Seleccionar tabla y realizar consulta.
    }

    public void amigos() {
        //Realizar consulta SQL from=amigos
    }

    public void general() {
        //Realizar consulta SQL from=*
    }

    public void cerrar() {
        ScreensFramework.stage.setWidth(921);
        ScreensFramework.stage.setHeight(590);
        myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);
    }
}
