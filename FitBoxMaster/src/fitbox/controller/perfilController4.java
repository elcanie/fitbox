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
import javafx.scene.control.Label;

/**
 *
 * @author Alejandro
 */
public class perfilController4 implements Initializable, ControlledScreen {

    private ScreensController myController = new ScreensController(ScreensFramework.inicialStage);
    private datosUsuario usuario;
    private Recurso recurso;
    @FXML
    private Label nombreLabel;
    @FXML
    private Label fechaLabel;
    @FXML
    private Label generoLabel;
    @FXML
    private Label correoLabel;
    @FXML
    private Label apellidosLabel;
    @FXML
    private Label pesoLabel;
    @FXML
    private Label alturaLabel;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
         recurso.putObject("usuario", usuario);
        myController.loadScreenInicial(ScreensFramework.PANTALLA_PERFIL5, ScreensFramework.PANTALLA_PERFIL5_FXML, recurso);
//           myController.setScreen(ScreensFramework.PANTALLA_PERFIL5);
    }

    @FXML
    private void handleButtonAnterior(ActionEvent event) {
        recurso.putObject("usuario", usuario);
        myController.loadScreenInicial(ScreensFramework.PANTALLA_PERFIL3, ScreensFramework.PANTALLA_PERFIL3_FXML, recurso);
//        myController.setScreen(ScreensFramework.PANTALLA_PERFIL3);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuario = new datosUsuario();
        recurso = (Recurso) rb;
        usuario = (datosUsuario) recurso.getObject("usuario");
        nombreLabel.setText(usuario.getNombre());
        apellidosLabel.setText(usuario.getApellidos());
        pesoLabel.setText(usuario.getPeso());
        alturaLabel.setText(usuario.getAltura());
        generoLabel.setText(usuario.getSexo());
        correoLabel.setText(usuario.getCorreo());
        String fecha= usuario.getDia()+"/"+usuario.getMes()+"/"+usuario.getAnyo();
        fechaLabel.setText(fecha);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}
