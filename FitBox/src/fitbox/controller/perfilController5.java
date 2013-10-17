/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.model.datosUsuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author usuario
 */
public class perfilController5 implements Initializable, ControlledScreen {

    private ScreensController myController;
    private datosUsuario usuario;
    private Recurso recurso;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        
    }

    @FXML
    private void handleButtonAnterior(ActionEvent event) {

        myController.loadScreen(ScreensFramework.PANTALLA_PERFIL4, ScreensFramework.PANTALLA_PERFIL4_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_PERFIL4);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuario = new datosUsuario();
        recurso = (Recurso) rb;
        usuario = (datosUsuario) recurso.getObject("usuario");
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}
