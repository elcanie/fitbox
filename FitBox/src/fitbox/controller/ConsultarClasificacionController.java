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
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    @FXML
    TableColumn columnaNombre;
    @FXML
    TableColumn columnaPuntos;

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }

    public void initialize(URL url, ResourceBundle rb) {
        ScreensFramework.stage.setWidth(386);
        ScreensFramework.stage.setHeight(337);
        this.recurso = (Recurso) rb;
        Ranking ranking = (Ranking) recurso.getObject("ranking");
        
        List<Ranking> lista;
        
        dal = new Dal();
        
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
