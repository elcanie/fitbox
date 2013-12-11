/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.controller.dao.Dal;
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

            
                progreso.setProgress(-0.1);
                progreso.setVisible(true);

        Dal dal = Dal.getDal();
        String nombreUser = fieldUser.getText();
        String pass = fieldPassword.getText();
        if (nombreUser.equals("") || pass.equals("")) {
            MessageBox.show(ScreensFramework.stage,
                    "Por favor, rellena los campos",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);
            return;
        }
        Collection<Usuario> datos = dal.find(Usuario.USUARIOSBYNOMBREYPASS, new Object[]{nombreUser, pass}, Usuario.class);
        //      Collection<Usuario> datos=dal.find(Usuario.TODOS_USUARIOS, null, Usuario.class);

        Iterator<Usuario> it = datos.iterator();
        Usuario user = null;
        if (it.hasNext()) {
            user = it.next();
        }

        if (user != null) {
            Recurso recurso = new Recurso();
            recurso.putObject("usuario", user);


//                    Parent root = null;
//                    try {
//                        recurso.putObject("controller", myController);
//                        root = FXMLLoader.load(getClass().getResource("/fitbox/view/PantallaPrincipal_2.fxml"), recurso);
//                    } catch (IOException ex) {
//                        Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    
//                    Scene scene = new Scene(root);
//                    ScreensFramework.stage.setScene(scene);

            //s.show();


            myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL, ScreensFramework.PANTALLA_PRINCIPAL_FXML, recurso);
            //myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);

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
}
