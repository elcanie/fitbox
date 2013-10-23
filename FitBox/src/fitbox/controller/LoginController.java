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
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import jfx.messagebox.MessageBox;

        
    
    
/**
 * FXML Controller class
 *
 * @author Lluis
 */
public class LoginController implements Initializable,ControlledScreen {
    
    @FXML private TextField fieldUser;
    @FXML private PasswordField fieldPassword;
    @FXML private Button buttonLogin;
    @FXML private Button btnRegistro;
    private ScreensController myController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //TODO
        ScreensFramework.stage.setWidth(543);
        ScreensFramework.stage.setHeight(438);
    }    
    @FXML private void registrar(ActionEvent event){
                myController.loadScreen(ScreensFramework.PANTALLA_PERFIL1, ScreensFramework.PANTALLA_PERFIL1_FXML, null);
        myController.setScreen(ScreensFramework.PANTALLA_PERFIL1);

    }
    @FXML private void iniciarSesion(ActionEvent event) {
        //Conexión SQL para comprobar los datos.
        
        Dal dal=Dal.getDal();
        String nombreUser=fieldUser.getText();
        String pass=fieldPassword.getText();
        if(nombreUser.equals("") || pass.equals("")){
                 MessageBox.show(ScreensFramework.stage,
                    "Por favor, rellena los campos",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);
                 return;
        }
        Collection<Usuario> datos=dal.find(Usuario.USUARIOSBYNOMBREYPASS, new Object[]{nombreUser,pass}, Usuario.class);
          //      Collection<Usuario> datos=dal.find(Usuario.TODOS_USUARIOS, null, Usuario.class);

        Iterator<Usuario> it=datos.iterator();
        Usuario user=null;
        if(it.hasNext())
            user=it.next();
        
        if(user!=null){
            Recurso recurso=new Recurso();
            recurso.putObject("usuario", user);
            myController.loadScreen(ScreensFramework.PANTALLA_PRINCIPAL,ScreensFramework.PANTALLA_PRINCIPAL_FXML,recurso );
                myController.setScreen(ScreensFramework.PANTALLA_PRINCIPAL);
            
            }
        else{
            MessageBox.show(ScreensFramework.stage,
                    "Introduzca un usuario y contraseña correctos",
                    "Information dialog",
                    MessageBox.ICON_INFORMATION | MessageBox.OK);
        }
            
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController=screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    

}
