/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.controller.dao.Dal;
import fitbox.controller.dao.Dao;
import fitbox.model.BaseDeDatos;
import fitbox.model.Jugador;
import fitbox.model.Ranking;
import fitbox.model.Usuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Jose
 */
public class ConsultarClasificacionController implements Initializable {

    private ScreensController myController = new ScreensController(ScreensFramework.stage);
    private List<Usuario> usuarios;
    private Recurso recurso;
    private List<Jugador> j;
    private Dal dal;
    @FXML
    private TableView tablaGeneral;
    @FXML
    private TableView tablaAmigos;
    private Usuario user;


    public void initialize(URL url, ResourceBundle rb) {
        this.recurso = (Recurso) rb;
        dal = Dal.getDal();
        this.user = (Usuario) recurso.getObject("usuario");
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
        
        tablaGeneral.getColumns().addAll(nombreColumn,puntosColumn);
          
         
         ObservableList<Ranking> datosRanking = FXCollections.observableArrayList(rankingList);
         tablaGeneral.setItems(datosRanking);

         amigos();
        //usuarios = (List<Usuario>) recurso.getObject("usuario");
        //j = (List<Jugador>)dal.find(Jugador.JUGADORBYUSUARIO, new Object[]{"*"}, Jugador.class).get(0);
        //Seleccionar tablaGeneral y realizar consulta.
    }

    public void amigos() {
        //Realizar consulta SQL from=amigos
        String sql = "SELECT U.nombre, J.puntos FROM jugador J, usuario U WHERE U.id = J.id AND J.id IN (SELECT idAmigo FROM amigo WHERE IdJugador = "+user.getId()+")ORDER BY J.puntos desc";
        Dao dao = new Dao();
        ObservableList<Ranking> datosRanking = dao.getPuntuacionesAmigos(sql);
        
        TableColumn nombreColumn = new TableColumn("Nombre");
        TableColumn puntosColumn = new TableColumn("Puntos");
        nombreColumn.setCellValueFactory(
                new PropertyValueFactory<Ranking, String>("nombre"));
        puntosColumn.setCellValueFactory(
                new PropertyValueFactory<Ranking, Double>("puntos"));
        
        tablaAmigos.getColumns().addAll(nombreColumn,puntosColumn);
        
        tablaAmigos.setItems(datosRanking);
    }

    public void general() {
        //Realizar consulta SQL from=*
    }


    
    
}
