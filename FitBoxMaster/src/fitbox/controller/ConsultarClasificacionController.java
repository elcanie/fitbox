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
        ScreensFramework.stage.setWidth(921);
        ScreensFramework.stage.setHeight(590);
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
        
        ScreensFramework.stage.setWidth(921);
        ScreensFramework.stage.setHeight(590);
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
    
     //Metodos barra de botones
    
      @FXML
    public void abrirPerfil(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_EDITARPERFIL, ScreensFramework.PANTALLA_EDITARPERFIL_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_EDITARPERFIL);
    }

    @FXML
    public void abrirActividades(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_ACTIVIDADES, ScreensFramework.PANTALLA_ACTIVIDADES_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_ACTIVIDADES);

    }

    @FXML
    public void abrirCalendario(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_VISTAMENSUAL, ScreensFramework.PANTALLA_VISTAMENSUAL_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_VISTAMENSUAL);


    }

    @FXML
    public void abrirVideos(MouseEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_SEGUIMIENTO, ScreensFramework.PANTALLA_SEGUIMIENTO_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_SEGUIMIENTO);


    }

    @FXML
    public void Actualizar(MouseEvent event) throws IOException{
        
    }

    @FXML
    public void abrirEquipo(MouseEvent event) throws IOException {
        // myController.setScreen(ScreensFramework.PANTALLA_EQUIPO);
    }

    @FXML
    public void abrirDesafios(MouseEvent event) throws IOException {
         myController.loadScreen(ScreensFramework.PANTALLA_DESAFIO, ScreensFramework.PANTALLA_DESAFIO_FXML, recurso);
         myController.setScreen(ScreensFramework.PANTALLA_DESAFIO);
    }

    @FXML
    public void abrirClasificacion(ActionEvent event) throws IOException {
        myController.loadScreen(ScreensFramework.PANTALLA_CLASIFICACION, ScreensFramework.PANTALLA_CLASIFICACION_FXML, recurso);
         myController.setScreen(ScreensFramework.PANTALLA_CLASIFICACION);
    }

    @FXML
    public void abrirAjustes(MouseEvent event) throws IOException {
        //myController.setScreen(ScreensFramework.PANTALLA_AJUSTES);
    }
   
    @FXML
    private void home(){
        myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);    
    }
    
}
