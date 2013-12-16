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
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

/**
 * FXML Controller class
 *
 * @author Lluis
 */
public class LoginController implements Initializable, ControlledScreen {

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
    private ScreensController myController;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        progreso.setVisible(false);
        ScreensFramework.stage.setWidth(543);
        ScreensFramework.stage.setHeight(438);
        ScreensFramework.stage.setResizable(false);
        ScreensFramework.stage.sizeToScene();
        ScreensFramework.stage.setMinHeight(0);
        ScreensFramework.stage.setMinWidth(0);
    }

    @FXML
    private void registrar(ActionEvent event) {
        myController.loadScreen(ScreensFramework.PANTALLA_PERFIL1, ScreensFramework.PANTALLA_PERFIL1_FXML, null);
        //myController.setScreen(ScreensFramework.PANTALLA_PERFIL1);

    }

    @FXML
    private void leerEnter(KeyEvent k) {
        if (KeyCode.ENTER == k.getCode()) {
            iniciarSesion(null);
        }
    }

    @FXML
    private void iniciarSesion(ActionEvent event) {
        //Conexión SQL para comprobar los datos.

       // Jugador _jugador = (Jugador) dal.find(Jugador.JUGADORBYUSUARIO, new Object[]{u.getId()}, Jugador.class).get(0);

        
        //Dal dal=Dal.getDal();
        String nombreUser=fieldUser.getText();
        String pass=fieldPassword.getText();
        if(nombreUser.equals("") || pass.equals("")){
                 MessageBox.show(ScreensFramework.stage,

                    "Por favor, rellena los campos",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);
            return;
        }

        //Collection<Usuario> datos=dal.find(Usuario.USUARIOSBYNOMBREYPASS, new Object[]{nombreUser,pass}, Usuario.class);
          //      Collection<Usuario> datos=dal.find(Usuario.TODOS_USUARIOS, null, Usuario.class);
        
//        Iterator<Usuario> it=
                
        Usuario user=BaseDeDatos.getBD().getUsuarioByPassANDName(nombreUser, pass);
//        if(it.hasNext())
//            user=it.next();
        
        if(user!=null){
            Recurso recurso=new Recurso();

            recurso.putObject("usuario", user);
            ScreensFramework.tituloVentanaNombreUsuario = " ("+user.getNombre()+")";
            


            cargarPantallas(recurso);
            if(!ScreensFramework.cargarPantalla(ScreensFramework.PANTALLA_PRINCIPAL))
            myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
            ScreensFramework.stage.centerOnScreen();
            
        } else {
            MessageBox.show(ScreensFramework.stage,
                    "Introduzca un usuario y contraseña correctos",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);
        }

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
    private void cargarPantallas(Recurso r){
        try {
            //PantallaPrincipal
            String name = ScreensFramework.PANTALLA_PRINCIPAL;
            String resource = ScreensFramework.PANTALLA_PRINCIPAL_FXML;
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource),r);
            Parent loadScreen = (Parent) myLoader.load();
            Scene scenePrincipal = new Scene(loadScreen);
            ScreensFramework.pantallas.put(name, scenePrincipal);
            
            //PantallaPerfil
            name = ScreensFramework.PANTALLA_EDITARPERFIL;
            resource = ScreensFramework.PANTALLA_EDITARPERFIL_FXML;
            myLoader = new FXMLLoader(getClass().getResource(resource),r);
            loadScreen = (Parent) myLoader.load();
            Scene scenePerfil = new Scene(loadScreen);
            ScreensFramework.pantallas.put(name, scenePerfil);
            
            //PantallaActividades
            name = ScreensFramework.PANTALLA_ACTIVIDADES;
            resource = ScreensFramework.PANTALLA_ACTIVIDADES_FXML;
            myLoader = new FXMLLoader(getClass().getResource(resource),r);
            loadScreen = (Parent) myLoader.load();
            Scene sceneActividades = new Scene(loadScreen);
            ScreensFramework.pantallas.put(name, sceneActividades);
            
            //PantallaVideos
            name = ScreensFramework.PANTALLA_SEGUIMIENTO;
            resource = ScreensFramework.PANTALLA_SEGUIMIENTO_FXML;
            myLoader = new FXMLLoader(getClass().getResource(resource),r);
            loadScreen = (Parent) myLoader.load();
            Scene sceneVideos = new Scene(loadScreen);
            ScreensFramework.pantallas.put(name, sceneVideos);
            
            //PantallaEventos
            name = ScreensFramework.PANTALLA_EVENTO;
            resource = ScreensFramework.PANTALLA_EVENTO_FXML;
            myLoader = new FXMLLoader(getClass().getResource(resource),r);
            loadScreen = (Parent) myLoader.load();
            Scene sceneEventos = new Scene(loadScreen);
            ScreensFramework.pantallas.put(name, sceneEventos);
            
            //PantallaDesafios
            name = ScreensFramework.PANTALLA_DESAFIO;
            resource = ScreensFramework.PANTALLA_DESAFIO_FXML;
            myLoader = new FXMLLoader(getClass().getResource(resource),r);
            loadScreen = (Parent) myLoader.load();
            Scene sceneDesafios = new Scene(loadScreen);
            ScreensFramework.pantallas.put(name, sceneDesafios);
            
            //PantallaCalendarioMensual
            name = ScreensFramework.PANTALLA_VISTAMENSUAL;
            resource = ScreensFramework.PANTALLA_VISTAMENSUAL_FXML;
            myLoader = new FXMLLoader(getClass().getResource(resource),r);
            loadScreen = (Parent) myLoader.load();
            Scene sceneCalendarioMensual = new Scene(loadScreen);
            ScreensFramework.pantallas.put(name, sceneCalendarioMensual);
            
             //PantallaCalendarioSemanal
            name = ScreensFramework.PANTALLA_VISTASEMANAL;
            resource = ScreensFramework.PANTALLA_VISTASEMANAL_FXML;
            myLoader = new FXMLLoader(getClass().getResource(resource),r);
            loadScreen = (Parent) myLoader.load();
            Scene sceneCalendarioSemanal = new Scene(loadScreen);
            ScreensFramework.pantallas.put(name, sceneCalendarioSemanal);
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
