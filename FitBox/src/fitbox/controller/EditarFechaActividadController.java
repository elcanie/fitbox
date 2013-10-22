/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fitbox.controller;

import com.sai.javafx.calendar.FXCalendar;
import static fitbox.controller.ConsultarVistaSemanalController.fxcalendar;
import fitbox.model.Actividad;
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
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Elias
 */
public class EditarFechaActividadController implements Initializable {

    @FXML
    TextArea tDescripcion;
    @FXML
    Label nombreActividad;
    @FXML
    TextField hora, minutos;
    @FXML
    HBox hPanel;
    static FXCalendar fxcalendar;
    private Recurso recurso;
    private ScreensController myController;
    private Actividad actividad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recurso = (Recurso) rb;
        actividad = (Actividad) recurso.getObject("Actividad");
        fxcalendar = new FXCalendar();
        hPanel.getChildren().addAll(fxcalendar);
        nombreActividad.setText(actividad.getNombre().toUpperCase());
        tDescripcion.setText(actividad.getDescripcion());
    }

    @FXML
    public void editar(MouseEvent event) {
        int horaInt = 0, minutosInt = 0;
        try {
            horaInt = Integer.parseInt(hora.getText());
            minutosInt = Integer.parseInt(minutos.getText());
        } catch (Exception e) {
            System.out.println("Solo numeros");
        }
        if (horaInt < 24 && horaInt >= 0 && minutosInt <= 59 && minutosInt >= 0) {
            myController.loadScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD, ScreensFramework.PANTALLA_REALIZARACTIVIDAD_FXML, recurso);
            myController.setScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD);
        }
        System.out.println("Ponga una hora y minutos correctos");
    }

    @FXML
    public void cancelar(MouseEvent event) {
        myController.loadScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD, ScreensFramework.PANTALLA_REALIZARACTIVIDAD_FXML, recurso);
        myController.setScreen(ScreensFramework.PANTALLA_REALIZARACTIVIDAD);
    }
}
