/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import fitbox.model.Actividad;
import fitbox.view.ControlledScreen;
import fitbox.view.Recurso;
import fitbox.view.ScreensFramework;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RUBEN
 */
public class DescripcionActividadController implements Initializable,ControlledScreen {

    @FXML
    Button bGo;
    @FXML
    TextArea tDescripcion;
    @FXML
    TextField factor, objetivo, categoria, nRepeticiones;
    @FXML
    Label nombreActividad;
    private Recurso recurso;
    private ScreensController myController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recurso = (Recurso) rb;
        Actividad actividad = (Actividad) recurso.getObject("actividad");
        setScreenParent((ScreensController)rb.getObject("controller"));
        nombreActividad.setText(actividad.getNombre().toUpperCase());
        tDescripcion.setText(actividad.getDescripcion());
        factor.setText(actividad.getFactor() + "");
        categoria.setText(actividad.getCategoria());
        nRepeticiones.setText(((int) (actividad.getFactor() * 9.75)) + "");
        objetivo.setText(actividad.getObjetivo());

    }

    @FXML
    public void irActividad(MouseEvent event) {
        Stage ventana=(Stage)bGo.getScene().getWindow();
        ventana.close();
        
        myController.loadScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD, ScreensFramework.PANTALLA_REALIZARACTIVIDAD_FXML,recurso );
        myController.setScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD);
        

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
       myController=screenParent; //To change body of generated methods, choose Tools | Templates.
    }
}
