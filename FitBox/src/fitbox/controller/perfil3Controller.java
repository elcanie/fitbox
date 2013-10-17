/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.view.ControlledScreen;
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
    
    //private Horario horario;
    private ScreensController myController;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    
     /*  horario=new Horario();
       horario.setLunes(true);
       horario.setMartes(true);
       horario.setLunes(true);
       horario.setLunes(true);*/
    }
    
    
    @FXML
    private void handleButtonAnterior(ActionEvent event) {
    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ScreensFramework.stage.setWidth(718);
        ScreensFramework.stage.setHeight(369);
       
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}
