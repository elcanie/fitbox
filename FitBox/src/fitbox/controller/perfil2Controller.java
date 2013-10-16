/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.view.ControlledScreen;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Alejandro
 */
public class perfil2Controller implements Initializable,ControlledScreen {

    @FXML
    private Button siguienteBT;
    @FXML
    private Button anteriorBT;
    @FXML
    private ComboBox comboDias;
    @FXML
    private CheckBox bicicletaCB;
    @FXML
    private CheckBox pesasCB;
    @FXML
    private CheckBox futbolCB;
    @FXML
    private CheckBox baloncestoCB;
    @FXML
    private CheckBox abdominalesCB;
    @FXML
    private CheckBox otrosCB;
    @FXML
    private TextField otrosEjerTF;
    /* @FXML
     private CheckBox 1;
     @FXML
     private CheckBox 2;
     @FXML
     private CheckBox 3;
     @FXML
     private CheckBox 4;*/
    //private Actividad actividad;
    private ScreensController myController;

    @FXML
    private void handleButtonAction(ActionEvent event) {
      /*  actividad = new Actividad();
        actividad.setComboDias(null);
        actividad.setBicicletaCB(null);
        actividad.setPesasCB(null);
        actividad.setFutbolCB(null);
        actividad.setBaloncestoCB(null);
        actividad.setAbdominalesCB(null);
        actividad.setOtrosCB(null);
        actividad.setOtrosEjerTF(null);
        //faltan categorias*/
    }

    @FXML
    private void handleButtonAtras(ActionEvent event) {
       /* actividad = new Actividad();
        actividad.setComboDias(null);
        actividad.setBicicletaCB(null);
        actividad.setPesasCB(null);
        actividad.setFutbolCB(null);
        actividad.setBaloncestoCB(null);
        actividad.setAbdominalesCB(null);
        actividad.setOtrosCB(null);
        actividad.setOtrosEjerTF(null);
        //faltan categorias*/
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  Recurso recurso= (Recurso) rb;
        //Usuario usuario= rb.getObject(recurso)
    }
        @Override
    public void setScreenParent(ScreensController screenParent) {
       myController=screenParent;
    }
}
