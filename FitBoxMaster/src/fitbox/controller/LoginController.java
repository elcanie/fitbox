/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.controller.dao.Dal;
import fitbox.model.BaseDeDatos;
import fitbox.model.Jugador;
import fitbox.model.Usuario;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.jws.soap.SOAPBinding;
import jfx.messagebox.MessageBox;

/**
 * FXML Controller class
 *
 * @author Lluis
 */
public class LoginController implements Initializable, ControlledScreen {
    @FXML Label labelAutenticando;
    @FXML AnchorPane panel;
    @FXML
    private ProgressIndicator progreso;
    @FXML
    private TextField fieldUser;
    @FXML
    private PasswordField fieldPassword;
    @FXML
    private Button buttonLogin;
    @FXML
    private Button btnRegistro;
    private ScreensController myController = new ScreensController(ScreensFramework.stage);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        ScreensFramework.inicialStage.setWidth(543);
        ScreensFramework.inicialStage.setHeight(440);
        ScreensFramework.inicialStage.setResizable(false);
        ScreensFramework.inicialStage.sizeToScene();
        
        fieldUser.setFocusTraversable(true);
        
    }

    @FXML
    private void registrar(ActionEvent event) {
        myController.loadScreenInicial(ScreensFramework.PANTALLA_PERFIL1, ScreensFramework.PANTALLA_PERFIL1_FXML, null);

        
        //myController.setScreen(ScreensFramework.PANTALLA_PERFIL1);

    }

    @FXML
    private void leerEnter(KeyEvent k) {
        if (KeyCode.ENTER == k.getCode()) {
            iniciarSesion(null);
        }
    }
    
     @FXML
    private void mostrarLabelEnter(KeyEvent k) {
        if (KeyCode.ENTER == k.getCode()) {
            mostrarLabel();
        }
    }

    @FXML
    private void iniciarSesion(MouseEvent event) {
 
        
        String nombreUser = fieldUser.getText();
        String pass = fieldPassword.getText();
        if (nombreUser.equals("") || pass.equals("")) {
            MessageBox.show(ScreensFramework.stage,
                    "Por favor, rellena los campos",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);
            labelAutenticando.setVisible(false);
            return;
        }



        Usuario user = BaseDeDatos.getBD().getUsuarioByPassANDName(nombreUser, pass);


        if (user != null) {
            Recurso recurso = new Recurso();

            recurso.putObject("usuario", user);
            Jugador j = BaseDeDatos.getBD().getJugador(user.getId());
            //ScreensFramework.tituloVentanaNombreUsuario = " (" + user.getNombre() + ")";
            ScreensFramework.tituloVentanaNombreUsuario = " (" + user.getNombre() + " " + j.getApellidos() + ")";



            cargarPantallas(recurso);
           

            if (!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_PRINCIPAL)) {
                myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
            }
            ScreensFramework.inicialStage.close();
            ScreensFramework.stage.show();
            ScreensFramework.stage.setResizable(true);

        } else {
            MessageBox.show(ScreensFramework.stage,
                    "Introduzca un usuario y contraseña correctos",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);
        }
        
        labelAutenticando.setVisible(false);

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }

    private void cargarPantallas(final Recurso r) {
        try {
//PantallaPrincipal
            String name = ScreensFramework.PANTALLA_PRINCIPAL;
            String resource = ScreensFramework.PANTALLA_PRINCIPAL_FXML;
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource),r);
            Parent loadScreen = (Parent) myLoader.load();
            AnchorPane anchorPrincipal =  (AnchorPane) loadScreen; 
            Scene scenePrincipal = new Scene(loadScreen);
            ScreensFramework.pantallas.put(name, scenePrincipal);
            ScreensFramework.paneles.put(name, anchorPrincipal);
            
            //PantallaPerfil
            name = ScreensFramework.PANTALLA_EDITARPERFIL;
            resource = ScreensFramework.PANTALLA_EDITARPERFIL_FXML;
            myLoader = new FXMLLoader(getClass().getResource(resource),r);
            loadScreen = (Parent) myLoader.load();
            AnchorPane anchorPerfil =  (AnchorPane) loadScreen;
            Scene scenePerfil = new Scene(loadScreen);
            scenePerfil.getStylesheets().add("/com/sai/javafx/calendar/styles/calendar_styles.css");
            ScreensFramework.pantallas.put(name, scenePerfil);
            ScreensFramework.paneles.put(name, anchorPerfil);
            
            //PantallaActividades
            name = ScreensFramework.PANTALLA_ACTIVIDADES;
            resource = ScreensFramework.PANTALLA_ACTIVIDADES_FXML;
            myLoader = new FXMLLoader(getClass().getResource(resource),r);
            loadScreen = (Parent) myLoader.load();
            AnchorPane anchorActividades =  (AnchorPane) loadScreen;
            Scene sceneActividades = new Scene(loadScreen);
            ScreensFramework.pantallas.put(name, sceneActividades);
            ScreensFramework.paneles.put(name, anchorActividades);
            
            //PantallaVideos
            name = ScreensFramework.PANTALLA_SEGUIMIENTO;
            resource = ScreensFramework.PANTALLA_SEGUIMIENTO_FXML;
            myLoader = new FXMLLoader(getClass().getResource(resource),r);
            loadScreen = (Parent) myLoader.load();
            AnchorPane anchorVideos =  (AnchorPane) loadScreen;
            Scene sceneVideos = new Scene(loadScreen);
            ScreensFramework.pantallas.put(name, sceneVideos);
            ScreensFramework.paneles.put(name, anchorVideos);
            
            //PantallaEventos
            name = ScreensFramework.PANTALLA_EVENTO;
            resource = ScreensFramework.PANTALLA_EVENTO_FXML;
            myLoader = new FXMLLoader(getClass().getResource(resource),r);
            loadScreen = (Parent) myLoader.load();
            AnchorPane anchorEventos =  (AnchorPane) loadScreen;
            Scene sceneEventos = new Scene(loadScreen);
            ScreensFramework.pantallas.put(name, sceneEventos);
            ScreensFramework.paneles.put(name, anchorEventos);
            
            //PantallaDesafios
            name = ScreensFramework.PANTALLA_DESAFIO;
            resource = ScreensFramework.PANTALLA_DESAFIO_FXML;
            myLoader = new FXMLLoader(getClass().getResource(resource),r);
            loadScreen = (Parent) myLoader.load();
            AnchorPane anchorDesafios =  (AnchorPane) loadScreen;
            Scene sceneDesafios = new Scene(loadScreen);
            sceneDesafios.getStylesheets().add("/com/sai/javafx/calendar/styles/calendar_styles.css");
            ScreensFramework.pantallas.put(name, sceneDesafios);
            ScreensFramework.paneles.put(name, anchorDesafios);
            
            //PantallaCalendarioMensual
            name = ScreensFramework.PANTALLA_VISTAMENSUAL;
            resource = ScreensFramework.PANTALLA_VISTAMENSUAL_FXML;
            myLoader = new FXMLLoader(getClass().getResource(resource),r);
            loadScreen = (Parent) myLoader.load();
            AnchorPane anchorCalendarioMensual =  (AnchorPane) loadScreen;
            Scene sceneCalendarioMensual = new Scene(loadScreen);
            ScreensFramework.pantallas.put(name, sceneCalendarioMensual);
            ScreensFramework.paneles.put(name, anchorCalendarioMensual);
            
             //PantallaCalendarioSemanal
            name = ScreensFramework.PANTALLA_VISTASEMANAL;
            resource = ScreensFramework.PANTALLA_VISTASEMANAL_FXML;
            myLoader = new FXMLLoader(getClass().getResource(resource),r);
            loadScreen = (Parent) myLoader.load();
            AnchorPane anchorCalendarioSemanal =  (AnchorPane) loadScreen;
            Scene sceneCalendarioSemanal = new Scene(loadScreen);
            sceneCalendarioSemanal.getStylesheets().add("/com/sai/javafx/calendar/styles/calendar_styles.css");
            ScreensFramework.pantallas.put(name, sceneCalendarioSemanal);
            ScreensFramework.paneles.put(name, anchorCalendarioSemanal);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    @FXML
    public void mostrarLabel(){labelAutenticando.setVisible(true);}
    
}
