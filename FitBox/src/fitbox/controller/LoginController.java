/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lluis
 */
public class LoginController implements Initializable {
    
    @FXML private TextField fieldUser;
    @FXML private TextField fieldPassword;
    @FXML private Button buttonLogin;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //TODO
    }    

    @FXML private void inciarSesion(ActionEvent event) {
        //Conexión SQL para comprobar los datos.
        throw new UnsupportedOperationException("No implementado aun."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
