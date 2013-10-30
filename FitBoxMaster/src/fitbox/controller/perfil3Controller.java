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
import javafx.scene.control.CheckBox;

/**
 *
 * @author Alejandro
 */
public class perfil3Controller implements Initializable, ControlledScreen{

    @FXML
    private CheckBox lunesCB;
    @FXML
    private CheckBox martesCB;
    @FXML
    private CheckBox miercolesCB;
    @FXML
    private CheckBox juevesCB;
    @FXML
    private CheckBox viernesCB;
    @FXML
    private CheckBox sabadoCB;
    @FXML
    private CheckBox domingoCB;
    
    private ScreensController myController;
    private datosUsuario usuario;
    private Recurso recurso;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
           usuario.setLunesCB(lunesCB.isSelected());
           usuario.setMartesCB(martesCB.isSelected());
           usuario.setMiercolesCB(miercolesCB.isSelected());
           usuario.setJuevesCB(juevesCB.isSelected());
           usuario.setViernesCB(viernesCB.isSelected());
           usuario.setSabadoCB(sabadoCB.isSelected());
           usuario.setDomingoCB(domingoCB.isSelected());
           
           recurso.putObject("usuario", usuario);   
           myController.loadScreen(ScreensFramework.PANTALLA_PERFIL4, ScreensFramework.PANTALLA_PERFIL4_FXML, recurso);
           myController.setScreen(ScreensFramework.PANTALLA_PERFIL4);
        

    }
    
    
    @FXML
    private void handleButtonAnterior(ActionEvent event) {
           usuario.setLunesCB(lunesCB.isSelected());
           usuario.setMartesCB(martesCB.isSelected());
           usuario.setMiercolesCB(miercolesCB.isSelected());
           usuario.setJuevesCB(juevesCB.isSelected());
           usuario.setViernesCB(viernesCB.isSelected());
           usuario.setSabadoCB(sabadoCB.isSelected());
           usuario.setDomingoCB(domingoCB.isSelected());
  
            myController.loadScreen(ScreensFramework.PANTALLA_PERFIL2, ScreensFramework.PANTALLA_PERFIL2_FXML, recurso);
            myController.setScreen(ScreensFramework.PANTALLA_PERFIL2);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuario = new datosUsuario();
        recurso = (Recurso) rb;
        usuario = (datosUsuario) recurso.getObject("usuario");
       
        try {
            lunesCB.setSelected(usuario.getLunesCB());
        } catch (Exception e) {
        }
        try {
            martesCB.setSelected(usuario.getMartesCB());
        } catch (Exception e) {
        }
        try {
            miercolesCB.setSelected(usuario.getMiercolesCB());
        } catch (Exception e) {
        }
        try {
            juevesCB.setSelected(usuario.getJuevesCB());
        } catch (Exception e) {
        }
        try {
            viernesCB.setSelected(usuario.getViernesCB());
        } catch (Exception e) {
        }
        try {
            sabadoCB.setSelected(usuario.getSabadoCB());
        } catch (Exception e) {
        }
        try {
            domingoCB.setSelected(usuario.getDomingoCB());
        } catch (Exception e) {
        }
   
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}
